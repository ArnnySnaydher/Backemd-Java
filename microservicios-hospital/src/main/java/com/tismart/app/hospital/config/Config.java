package com.tismart.app.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class Config implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**")  // Permite CORS en todas las rutas
         .allowedOrigins("http://localhost:4200")  // Permite solicitudes solo del origen de Angular
         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos HTTP permitidos
         .allowedHeaders("*")  // Permite todos los headers
         .allowCredentials(true)  // Permitir cookies y otros datos de autenticación
         .maxAge(3600);  // Máximo tiempo que los resultados de la solicitud pueden ser cacheados
 
	}

}
