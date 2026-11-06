
package com.rolosmotoscars.backend.configuracion;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuración de seguridad para los servicios REST.
 */
@Configuration
public class ConfiguracionSeguridad {

    /**
     * Define BCrypt para proteger las contraseñas.
     */
    @Bean
    public PasswordEncoder codificadorPassword() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura los permisos CORS para permitir la comunicación
     * entre el frontend React y el backend Spring Boot.
     */
    @Bean
    public CorsConfigurationSource configuracionCors() {

        CorsConfiguration configuracion = new CorsConfiguration();

        configuracion.setAllowedOrigins(
            List.of("http://localhost:5173")
        );

        configuracion.setAllowedMethods(
            List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        configuracion.setAllowedHeaders(
            List.of("*")
        );

        configuracion.setAllowCredentials(false);
        configuracion.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource origen =
            new UrlBasedCorsConfigurationSource();

        origen.registerCorsConfiguration("/**", configuracion);

        return origen;
    }

    /**
     * Configura el acceso a los servicios de autenticación.
     */
    @Bean
    public SecurityFilterChain cadenaSeguridad(HttpSecurity http)
            throws Exception {

        http
            .cors(cors -> cors.configurationSource(configuracionCors()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(autorizacion -> autorizacion
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
