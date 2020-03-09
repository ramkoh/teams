package com.edu.postgrad.game.teams.rest.selenium;

import java.util.ArrayList;
import java.util.List;

import com.edu.postgrad.game.common.Player;
import com.edu.postgrad.game.teams.TeamsApplication;
import com.edu.postgrad.game.teams.rest.PlayerController;
import com.edu.postgrad.game.teams.service.PlayerService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsApplication.class)
@ContextConfiguration(classes = TeamsApplication.class)
public class ViewAllPlayersSelTest {

     WebDriver webdriver = new FirefoxDriver();

    @Before
    public void initialization() {
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
    }
    @After
    public void tearDown(){
        webdriver.close();
    }
    @Autowired
    private PlayerController playerController;

    @Autowired
    private PlayerService playerService;

    int playersCount =0;

    List<String> expectedFirstNames = new ArrayList<>();

    @When("^When User clicks on players$")
    public void when_User_clicks_on_players() throws Throwable {
        List<Player> players = new ArrayList<>();
        playerService.getAllPlayers().forEach(players::add);
        players.forEach(player -> expectedFirstNames.add(player.getFirstName()));
        playersCount = players.size();
        webdriver.get("http://localhost:8081/players");
    }

    @Then("^Page contains a list of players$")
    public void page_contains_a_list_of_players() throws Throwable {
        String title = webdriver.getTitle();
        List<String> firstNames = new ArrayList<>();
        List<WebElement> firstColumn = webdriver.findElements(By.xpath(".//*[@id=\"table_container\"]/table/tbody/tr/td[1]"));
        Assertions.assertEquals( firstColumn.size(), playersCount );

        firstColumn.forEach(p ->firstNames.add(p.getText()));

        Assertions.assertEquals(firstNames, (expectedFirstNames));
    }
}
