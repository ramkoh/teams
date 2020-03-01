package com.edu.postgrad.game.teams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.edu.postgrad"})
public class TeamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamsApplication.class, args);
	}
	/*@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}*/
}

