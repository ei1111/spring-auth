package com.spring_auth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                //API 마다 인증 적용
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                //API 마다 토큰 적용 버튼 셍김
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("spring 인증 프로젝트")
                .description("spring doc을 사용한 auth swagger UI")
                .version("1.0");
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
