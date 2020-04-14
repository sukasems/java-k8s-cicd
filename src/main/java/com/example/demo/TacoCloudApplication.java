package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Application starter
 * @author EUTyrin
 *
 */

// if we want empty controllers we can implement WebMvcConfigurer
@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer{

	// App start
	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	// Add some empty controllers
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// just redirect / http calls to home.html
		registry.addViewController("/").setViewName("home");
	}
	
	

}
