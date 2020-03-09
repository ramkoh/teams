package com.edu.postgrad.game.teams.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
//@ContextConfiguration(classes={PropertiesContext.class})
public class CucumberContext {
    static {
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
    }
//    @Autowired
//   private WebDriver driver;

    @Bean(name="webdriver", destroyMethod="close")
    public WebDriver getWebDriver() {
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
        return new ChromeWebDriver();
      // return new ChromeDriver();

    }

}
