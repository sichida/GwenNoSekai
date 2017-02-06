package fr.ichida.cms.feature;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import fr.ichida.cms.domain.Article;
import fr.ichida.cms.domain.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StreamUtils;

import java.io.InputStream;

/**
 * Created by shoun on 06/02/2017.
 */
public class PictureStepDef {
    @Autowired
    private ArticleRestService articleRestService;
    @Autowired
    private PictureRestService pictureRestService;

    @When("^I upload the picture \"([^\"]*)\" for the article described by \"([^\"]*)\"$")
    public void iUploadThePictureForTheArticleDescribedBy(String filename, String id) throws Throwable {
        InputStream stream = new ClassPathResource("data/thumbs/" + filename).getInputStream();
        MockMultipartFile file = new MockMultipartFile(filename, StreamUtils.copyToByteArray(stream));

        Picture thumbnail = pictureRestService.uploadThumbnail(file).getBody();
        Article article = articleRestService.findById(id).getBody();
        article.setThumbnailId(thumbnail.getId());
        articleRestService.save(article);
    }
}
