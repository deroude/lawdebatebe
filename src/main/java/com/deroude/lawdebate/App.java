/*
 * Copyright 2017  Valentin Raduti.
 * This source code file is the property of Valentin Raduti.
 * You are not allowed to view, edit, copy, re-use or re-engineer
 * this source code file unless specifically permissioned by Valentin Raduti.
 */
package com.deroude.lawdebate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author valentin.raduti
 */
@SpringBootApplication
@EnableScheduling
//@EnableSwagger2
public class App {

    public static void main(String[] args) throws Exception {
	SpringApplication.run(App.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurerAdapter() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
	    }
	};
    }

//    @Bean
//    public Docket newsApi() {
//	return new Docket(DocumentationType.SWAGGER_2)
//		.groupName("api")
//		.apiInfo(apiInfo())
//		.select()
//		.paths(regex("/api.*"))
//		.build();
//    }
//
//    private ApiInfo apiInfo() {
//	return new ApiInfoBuilder()
//		.title("Perfect Gift REST API")
//		.description("Perfect Gift is an application designed to choose a gift tailored for a person, by answering a set of questions")
//		.contact(new Contact("Valentin Raduti", "http://www.deroude.ro", "rvgabriel@yahoo.com"))
//		.license("Commercial License")
//		.version("1.0")
//		.build();
//    }
}
