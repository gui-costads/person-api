package com.person.personapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder()
                .group("personapi")
                .pathsToMatch("/**")
                .build();
    }
}
