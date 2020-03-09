package com.edu.postgrad.game.teams.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/cucumber"},
        plugin = {
        "pretty",
        "html:target/cucumber"
})
public class CucumberRunnerTest {
}
