package com.play.tube.app;


import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;

import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;

@EnableOpenApi
@SpringBootApplication(scanBasePackages = "com.play.tube")
@EntityScan(basePackages = "com.play.tube.entity")
@EnableJpaRepositories(basePackages = "com.play.tube.dao")
public class SpringDataRestPlayTubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestPlayTubeApplication.class, args);
	
	}
	@Bean
	public Docket openApiPetStore() {
		return new Docket(DocumentationType.OAS_30)
				.groupName("open-api-user-management")
				.select()
				.paths(rolePaths())
				.build();
	}

	private Predicate<String> rolePaths() {
		return springfox.documentation.builders.PathSelectors.regex(".*/usm/.*");
	}
	

	
}
