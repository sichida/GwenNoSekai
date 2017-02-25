package fr.ichida.cms.feature;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.ichida.cms.GwennosekaiJavaApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by shoun on 25/02/2017.
 */
@ContextConfiguration(classes = GwennosekaiJavaApplication.class)
public class TagStepdefs {
    @Autowired
    private TagRestService tagRestService;
    private ResponseEntity result;

    @When("^I request existing tags with query \"([^\"]*)\"$")
    public void iRequestExistingTagsWithQueryTag(String query) throws Throwable {
        result = tagRestService.query(query);
    }

    @Then("^I should have the following tags:$")
    public void iShouldHaveTheFollowingTags(List<String> tags) throws Throwable {
        assertThat((List<String>) result.getBody()).isEqualTo(tags);
    }
}
