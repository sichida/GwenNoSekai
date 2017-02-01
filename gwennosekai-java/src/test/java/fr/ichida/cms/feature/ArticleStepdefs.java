package fr.ichida.cms.feature;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.ichida.cms.GwennosekaiJavaApplication;
import fr.ichida.cms.domain.Article;
import fr.ichida.cms.mongo.ArticleRepository;
import org.assertj.core.groups.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
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
    private ResponseEntity result;

    @Before
    public void setup() {
        this.expectedArticle = null;
    }

    @Before("@mongo-txn")
    public void clearMongoCollections() {
        mongoTemplate.getCollectionNames().forEach(name -> {
            mongoTemplate.remove(new Query(), name);
        });
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

    @Given("^the following articles exist:$")
    public void theFollowingArticlesExist(List<Article> articles) throws Throwable {
        articles.forEach(articleRepository::save);
    }

    @When("^I display (\\d+) article from page (\\d+)$")
    public void iDisplayArticleFromPage(int nbArticles, int page) throws Throwable {
        this.result = articleRestService.query(page - 1, nbArticles);
    }

    @Then("^I should display (\\d+) article$")
    public void iShouldDisplayArticle(int nbArticle) throws Throwable {
        assertThat(((ResponseEntity<Page<Article>>) this.result).getBody().getContent().size())
                .isEqualTo(nbArticle);
    }

    @And("^we should find the article in results$")
    public void weShouldFindTheArticleInResults(List<Article> articles) throws Throwable {
        Article ref = articles.get(0);
        Tuple refData = tuple(ref.getTitle(), ref.getContent(), ref.getPermalink(), ref.getAuthor());
        assertThat(((ResponseEntity<Page<Article>>) this.result).getBody().getContent())
                .extracting("title", "content", "permalink", "author").contains(refData);
    }

    @When("^I request the article with id \"([^\"]*)\"$")
    public void iRequestTheArticleWithId(String id) throws Throwable {
        this.result = this.articleRestService.findById(id);
    }

    @Then("^I should find this article$")
    public void iShouldFindThisArticle(List<Article> articles) throws Throwable {
        Article ref = articles.get(0);
        Tuple refData = tuple(ref.getTitle(), ref.getContent(), ref.getPermalink(), ref.getAuthor());
        Article expected = ((ResponseEntity<Article>) this.result).getBody();
        Tuple expectedData = tuple(expected.getTitle(), expected.getContent(), expected.getPermalink(), expected.getAuthor());
        assertThat(expectedData).isEqualTo(refData);
    }
}
