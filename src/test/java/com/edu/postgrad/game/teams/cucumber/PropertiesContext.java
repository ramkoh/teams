//package com.edu.postgrad.game.teams.cucumber;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
//public class PropertiesContext {
//    @Value("${selenium.screenshotOnFailure}")
//    private String screenshotOnFailure;
//
//    @Bean("screenshotOnFailure")
//    public boolean takeScreenshotOnFailure() {
//        return Boolean.parseBoolean(screenshotOnFailure);
//    }
//
//    @Value("${selenium.screenshotDestinationFolder}")
//    private String screenshotDestinationFolder;
//
//    @Bean("screenshotDestinationFolder")
//    public String getScreenshotDestinationFolder() {
//        return screenshotDestinationFolder;
//    }
//
//    @Value("${selenium.webbrowser}")
//    private String webbrowser;
//
//   /* @Bean("webbrowser")
//    public String getWebbrowser() {
//        return webbrowser;
//    }*/
//
//}
