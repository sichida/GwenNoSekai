package fr.ichida.cms.feature;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.ichida.cms.domain.Article;
import fr.ichida.cms.domain.Picture;
import fr.ichida.cms.mongo.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Created by shoun on 06/02/2017.
 */
public class PictureStepDef {
    @Autowired
    private ArticleRestService articleRestService;
    @Autowired
    private PictureRestService pictureRestService;
    @Autowired
    private PictureRepository pictureRepository;
    private ResponseEntity response;

    @Before
    public void setup() {
        this.response = null;
    }

    @When("^I upload the picture \"([^\"]*)\" for the article described by \"([^\"]*)\"$")
    public void iUploadThePictureForTheArticleDescribedBy(String filename, String id) throws Throwable {
        InputStream stream = new ClassPathResource("data/pictures/" + filename).getInputStream();
        MockMultipartFile file = new MockMultipartFile(filename, StreamUtils.copyToByteArray(stream));

        Picture thumbnail = pictureRestService.uploadThumbnail(file).getBody();
        Article article = articleRestService.findById(id).getBody();
        article.setThumbnailId(thumbnail.getId());
        articleRestService.save(article);
    }

    @Given("^the pictures exist:$")
    public void thePictureExists(DataTable pictures) throws Throwable {
        pictures.cells(1).forEach(row -> {
            Picture picture = new Picture();
            picture.setId(row.get(0));
            try {
                InputStream stream = new ClassPathResource("data/pictures/" + row.get(1)).getInputStream();
                picture.setData(StreamUtils.copyToByteArray(stream));
            } catch (IOException e) {
                fail("Cannot find picture '" + row.get(1) + "'");
            }
            pictureRepository.save(picture);
        });
    }

    @When("^I display the picture \"([^\"]*)\"$")
    public void iDisplayThePicture(String id) throws Throwable {
        this.response = pictureRestService.getContent(id);
    }

    @Then("^the picture \"([^\"]*)\" should have same content that \"([^\"]*)\" file$")
    public void thePictureShouldHaveSameContentThatFile(String id, String filename) throws Throwable {
        Picture picture = pictureRepository.findOne(id);
        assertThat(picture).isNotNull();
        InputStream stream = new ClassPathResource("data/pictures/" + filename).getInputStream();
        byte[] expected = StreamUtils.copyToByteArray(stream);
        assertThat(picture.getData()).isEqualTo(expected);
        assertThat(this.response.getBody()).isEqualTo(expected);
    }

    @When("^I request the picture \"([^\"]*)\"$")
    public void iRequestThePicture(String pictureId) throws Throwable {
        response = pictureRestService.findById(pictureId);
    }

    @Then("^I should have a non null picture$")
    public void iShouldHaveANonNullPicture() throws Throwable {
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }
}
