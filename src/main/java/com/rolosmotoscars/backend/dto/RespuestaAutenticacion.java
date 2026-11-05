
package com.rolosmotoscars.backend.dto;

/**
 * Estructura de las respuestas del servicio de autenticación.
 */
public record RespuestaAutenticacion(
    boolean autenticado,
    String mensaje,
    String nombreUsuario
) {
}
