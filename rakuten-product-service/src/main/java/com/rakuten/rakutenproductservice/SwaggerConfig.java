package com.rakuten.rakutenproductservice;
/**
 * Swagger2 configuration class
 * 
 * To be used only when swagger2 has to activated
 * 
 * @author adarshsumma
 *
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Enabling swagger configuration
 * 
 * @author adarshsumma
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	List<SecurityScheme> schemeList = new ArrayList<>();
	
	 @Bean
	    public Docket apiDocket() {
		 schemeList.add(new BasicAuth("basicAuth"));
	        return new Docket(DocumentationType.SWAGGER_2).securitySchemes(schemeList)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.rakuten.rakutenproductservice"))
	                .paths(PathSelectors.any())
	                .build().apiInfo(metaData());
	    }
	 
	 private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Rakuten Product Service")
	                .description("\"Rakuten product service \"")
	                .version("1.0.0")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	                .contact(new Contact("Adarsh S", "", "adu11188@gmail.com"))
	                .build();
	    }
}
