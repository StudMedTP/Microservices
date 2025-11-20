package com.studmed.user.shared;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI platformOpenApi() {
        var openApi = new OpenAPI();
        openApi.info(new Info().title("Backend User").version("v1.0.0"));
        openApi.servers(List.of(
                new Server().url("/").description("Direct Microservice"),
                new Server().url("/microservice-user").description("Through API Gateway"))
        );
        openApi.addSecurityItem(new SecurityRequirement().addList("authToken"));
        openApi.components( new Components().addSecuritySchemes("authToken",
                new SecurityScheme().name("Authorization").type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER)));
        return openApi;
    }
}
