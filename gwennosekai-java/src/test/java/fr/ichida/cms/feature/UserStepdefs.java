package fr.ichida.cms.feature;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import fr.ichida.cms.GwennosekaiJavaApplication;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by shoun on 31/01/2017.
 */
@ContextConfiguration(classes = GwennosekaiJavaApplication.class)
public class UserStepdefs {
    @Given("^\"([^\"]*)\" is logged in$")
    public void isLoggedIn(String username) throws Throwable {
        // Nothing for now
    }
}
