package com.oriedroc.rest.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Rest API")
                    .version("v1")
                    .description("describe API")
                    .termsOfService("termos")
                    .license(
                        License().name("Apache 2.0")
                            .url("url")
                    )
            )
    }
}