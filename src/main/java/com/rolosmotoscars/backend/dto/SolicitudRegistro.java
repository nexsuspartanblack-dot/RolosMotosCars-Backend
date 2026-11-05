
package com.rolosmotoscars.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Recibe los datos necesarios para registrar un usuario.
 */
public record SolicitudRegistro(

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 3, max = 50,
          message = "El usuario debe tener entre 3 y 50 caracteres.")
    String nombreUsuario,

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, max = 72,
          message = "La contraseña debe tener entre 8 y 72 caracteres.")
    String password

) {
}
