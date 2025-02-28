package com.example.api_biblioteca.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todos los endpoints
                .allowedOrigins("*") // Permite solicitudes desde cualquier origen
                .allowedMethods("*") // Permite todos los métodos HTTP
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(false); // Desactiva credenciales (cookies, tokens, etc.)
    }
}