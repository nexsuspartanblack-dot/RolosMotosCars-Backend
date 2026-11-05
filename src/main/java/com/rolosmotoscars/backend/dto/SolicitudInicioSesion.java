
package com.rolosmotoscars.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Recibe las credenciales para iniciar sesión.
 */
public record SolicitudInicioSesion(

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(max = 50)
    String nombreUsuario,

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(max = 72)
    String password

) {
}
