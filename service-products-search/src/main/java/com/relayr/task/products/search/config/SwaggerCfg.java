package com.relayr.task.products.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.singletonList;

/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
@Configuration
public class SwaggerCfg {

    /**
     * Group Product contains product operations
     */
    @Bean
    public Docket swaggerProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Product")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.relayr.task.products.search.restapi.v1"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(singletonList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Relayr task - REST APIs")
                .description("Product comparison service.").termsOfServiceUrl("")
                .contact(new Contact("Nassim MOUALEK", "https://github.com/nassimus26", "cd_boite@yahoo.fr"))
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }
}
