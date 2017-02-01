package fr.ichida.cms;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by shoun on 04/01/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        features = {"classpath:features/"},
        glue = {"cucumber.api.spring", "fr.ichida.cms.feature"},
        format = {"html:target/cucumber", "json:target/cucumber.json"}
)
public class RunCukesTest {
}
