package fr.ichida.cms.feature;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.ichida.cms.GwennosekaiJavaApplication;
import fr.ichida.cms.domain.Article;
import fr.ichida.cms.mongo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by shoun on 31/01/2017.
 */
@ContextConfiguration(classes = GwennosekaiJavaApplication.class)
public class ArticleStepdefs {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ArticleRestService articleRestService;
    private Article expectedArticle;

    @Before
    public void setup() {
        this.expectedArticle = null;
    }

    @When("^he creates the article$")
    public void heCreatesTheArticle(List<Article> articles) throws Throwable {
        articleRestService.save(articles.get(0));
    }

    @Then("^it should be (\\d+) article$")
    public void itShouldBeArticle(int nbArticles) throws Throwable {
        assertThat(articleRepository.count()).isEqualTo(nbArticles);
    }

    @And("^we should find the article$")
    public void weShouldFindTheArticle(List<Article> articles) throws Throwable {
        Article reference = articles.get(0);
        this.expectedArticle = mongoTemplate.findOne(query(
                where("title").is(reference.getTitle())
                        .and("content").is(reference.getContent())
//                        .and("author").is(reference.getAuthor())
                        .and("permalink").is(reference.getPermalink())), Article.class);
        assertThat(expectedArticle).as("Article").isNotNull();
    }

    @And("^it creation date should be today$")
    public void itCreationDateShouldBeToday() throws Throwable {
        assertThat(this.expectedArticle.getCreationDate()).isNotNull();
        assertThat(this.expectedArticle.getCreationDate().getYear()).isEqualTo(LocalDateTime.now().getYear());
        assertThat(this.expectedArticle.getCreationDate().getMonth()).isEqualTo(LocalDateTime.now().getMonth());
        assertThat(this.expectedArticle.getCreationDate().getDayOfMonth()).isEqualTo(LocalDateTime.now().getDayOfMonth());
    }
}
