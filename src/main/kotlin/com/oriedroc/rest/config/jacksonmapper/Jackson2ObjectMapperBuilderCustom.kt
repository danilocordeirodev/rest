package com.oriedroc.rest.config.jacksonmapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class Jackson2ObjectMapperBuilderCustom {
    @Bean
    fun objectMapperBuilder(): Jackson2ObjectMapperBuilder? {
        return object : Jackson2ObjectMapperBuilder() {
            override fun configure(objectMapper: ObjectMapper) {
                super.configure(objectMapper)
                objectMapper.registerModule(JavaTimeModule())
            }
        }
    }
}
