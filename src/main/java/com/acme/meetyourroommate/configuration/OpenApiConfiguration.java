package com.acme.meetyourroommate.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "meetYourRoommateOpenApi")
    public OpenAPI meetYourRoommateOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("MeetYourRoommate Application API")
                        .description(
                                "MeetYourRoommate API implemented with Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0"));

    }

}
