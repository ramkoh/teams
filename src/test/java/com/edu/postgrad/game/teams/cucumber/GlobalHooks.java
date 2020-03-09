package com.edu.postgrad.game.teams.cucumber;

import cucumber.api.java.Before;

public class GlobalHooks {
    @Before
    public void beforeAll() {
            //Runtime.getRuntime().addShutdownHook(afterAllThread);
            // do the beforeAll stuff...
        System.setProperty("webdriver.gecko.driver","/home/eramkoh/Tools/geckodriver");
        }


}
