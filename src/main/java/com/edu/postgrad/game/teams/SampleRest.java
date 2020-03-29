package com.edu.postgrad.game.teams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class SampleRest {
    @Value("${teams-service.prop1}")
    private String prop1;

    @Autowired
    TeamsConfiguration config;


    @GetMapping("hello1")
    public String hello(){
        return prop1;
    }

    @GetMapping("hello2")
    public String hello2(){
        return config.getPlayerFirstName();
    }

    @GetMapping("invoke/{serviceId}/{serviceDescription}")
    public String getServiceInfo(@PathVariable int serviceId, @PathVariable String serviceDescription){
        System.err.println(String.format("getServiceInfo from Teams invoked with serviceId: %d " +
                "and description: %s", serviceId, serviceDescription));
        return serviceDescription;
    }

}
