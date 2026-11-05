
package com.rolosmotoscars.backend.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
     * Configura el acceso a los servicios de autenticación.
     */
    @Bean
    public SecurityFilterChain cadenaSeguridad(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(autorizacion -> autorizacion
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
