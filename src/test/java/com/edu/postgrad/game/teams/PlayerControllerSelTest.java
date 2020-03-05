package com.edu.postgrad.game.teams;
import static org.assertj.core.api.Assertions.assertThat;

import com.edu.postgrad.game.teams.SeleniumTest;
import com.edu.postgrad.game.teams.TeamsApplication;
import com.edu.postgrad.game.teams.rest.PlayerController;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TeamsApplication.class)
@SeleniumTest
public class PlayerControllerSelTest {
    @Autowired
    private WebDriver driver;

    private PlayerController playerController;

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");

    }

    @Test
    public void containsActuatorLinks() {
        driver.get("http://localhost:8081/teams");
        String title = driver.getTitle();
        assertThat(title.equals( "Taco Cloud"));
    }
   /* @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:8081/teams" ,
                String.class)).contains("Teams list");
    }*/

}
