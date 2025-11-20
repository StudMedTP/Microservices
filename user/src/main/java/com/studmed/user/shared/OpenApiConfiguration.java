package com.studmed.user.shared;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfiguration {

    private final Environment environment;

    public OpenApiConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public OpenAPI platformOpenApi() {
        var openApi = new OpenAPI();
        openApi.info(new Info().title("Backend User").version("v1.0.0"));

        String activeProfile = environment.getProperty("spring.profiles.active", "default");
        List<Server> servers = new ArrayList<>();
        if ("prod".equals(activeProfile)) {
            servers.add(new Server().url("https://studmed.aurumtech.site/user").description("Domain Direct Microservice"));
            servers.add(new Server().url("https://studmed.aurumtech.site/api-gateway/microservice-user").description("Domain Through API Gateway"));
            servers.add(new Server().url("/").description("Direct Microservice"));
            servers.add(new Server().url("/microservice-user").description("Through API Gateway"));
        } else {
            servers.add(new Server().url("/").description("Direct (microservice)"));
            servers.add(new Server().url("/microservice-user").description("Through API Gateway"));
        }
        openApi.servers(servers);
        openApi.addSecurityItem(new SecurityRequirement().addList("authToken"));
        openApi.components( new Components().addSecuritySchemes("authToken",
                new SecurityScheme().name("Authorization").type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER)));
        return openApi;
    }
}
