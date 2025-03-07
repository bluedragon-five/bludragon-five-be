package com.example.bluedragon.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addServersItem(server())
                .info(getInfo());
    }

    private Server server() {
        return new Server().url("/");
    }

    private Info getInfo() {
        return new Info().title("청룡톤 5팀 API 문서")
                .version("1.0.0");
    }
}

