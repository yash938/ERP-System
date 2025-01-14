package com.Erp_System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for Swagger API documentation.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates and configures a Swagger Docket bean.
     *
     * @return a configured Docket instance
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .securityContexts(Arrays.asList(getSecurityContext()))
                .securitySchemes(Arrays.asList(getSchemes()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Configures the security context for Swagger.
     *
     * @return a configured SecurityContext instance
     */
    private SecurityContext getSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(getSecurityReferences())
                .build();
    }

    /**
     * Defines security references for API authentication.
     *
     * @return a list of SecurityReference objects
     */
    private List<SecurityReference> getSecurityReferences() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("Global", "Access Every Thing")
        };
        return Arrays.asList(new SecurityReference("JWT", scopes));
    }

    /**
     * Defines the API key for JWT authentication.
     *
     * @return an ApiKey object
     */
    private ApiKey getSchemes() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    /**
     * Provides API information for Swagger documentation.
     *
     * @return an ApiInfo object
     */
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Electronic Store Backend: APIs",
                "This is a backend project created by LCWD.",
                "1.0.0",
                "https://www.learncodewithdurgesh.com",
                new Contact(
                        "Durgesh",
                        "https://www.instagram.com/durgesh_k_t",
                        "learncodewithdurgesh@gmail.com"
                ),
                "License of APIs",
                "https://www.learncodewithdurgesh.com/about",
                List.of()
        );
    }
}
