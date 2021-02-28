package com.loan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
@Configuration
@Profile("!test")
public class SwaggerConfig {
	
	@Bean
	public Docket postsApi() {
		return new Docket(SWAGGER_2).apiInfo(apiInfo()).select().apis(basePackage("com.loan.controllers")).build().enable(true);
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Loan Service").description("Loan service").version("1.0").build();
	}

}
