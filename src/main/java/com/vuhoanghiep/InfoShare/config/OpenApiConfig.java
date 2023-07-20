package com.vuhoanghiep.InfoShare.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//link: http://localhost:8080/swagger-ui/index.html
//key : /v3/api-docs
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "API InfoShare",
        description = "API Documentation for My Spring Boot Application",
        version = "1.0"
))
public class OpenApiConfig<ApiInfo> {
}
