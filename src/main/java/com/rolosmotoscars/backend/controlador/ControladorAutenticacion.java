
package com.rolosmotoscars.backend.controlador;

import com.rolosmotoscars.backend.dto.RespuestaAutenticacion;
import com.rolosmotoscars.backend.dto.SolicitudInicioSesion;
import com.rolosmotoscars.backend.dto.SolicitudRegistro;
import com.rolosmotoscars.backend.entidad.Usuario;
import com.rolosmotoscars.backend.servicio.ServicioAutenticacion;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Expone los servicios web de registro e inicio de sesión.
 */
@RestController
@RequestMapping("/api/auth")
public class ControladorAutenticacion {

    private final ServicioAutenticacion servicioAutenticacion;

    public ControladorAutenticacion(
            ServicioAutenticacion servicioAutenticacion) {

        this.servicioAutenticacion = servicioAutenticacion;
    }

    /**
     * Registra un nuevo usuario.
     */
    @PostMapping("/registro")
    public ResponseEntity<RespuestaAutenticacion> registrar(
            @Valid @RequestBody SolicitudRegistro datos) {

        Usuario usuario = servicioAutenticacion.registrar(datos);

        RespuestaAutenticacion respuesta = new RespuestaAutenticacion(
            true,
            "Usuario registrado correctamente.",
            usuario.getNombreUsuario()
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(respuesta);
    }

    /**
     * Comprueba las credenciales del usuario.
     */
    @PostMapping("/login")
    public ResponseEntity<RespuestaAutenticacion> iniciarSesion(
            @Valid @RequestBody SolicitudInicioSesion datos) {

        boolean valido = servicioAutenticacion.autenticar(
            datos.nombreUsuario(),
            datos.password()
        );

        if (valido) {
            return ResponseEntity.ok(
                new RespuestaAutenticacion(
                    true,
                    "Autenticación satisfactoria.",
                    datos.nombreUsuario()
                )
            );
        }

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(
                new RespuestaAutenticacion(
                    false,
                    "Error de autenticación: usuario o contraseña incorrectos.",
                    null
                )
            );
    }
}
