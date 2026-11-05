
package com.rolosmotoscars.backend.servicio;

import com.rolosmotoscars.backend.dto.SolicitudRegistro;
import com.rolosmotoscars.backend.entidad.Usuario;
import com.rolosmotoscars.backend.repositorio.RepositorioUsuarios;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Contiene la lógica de registro e inicio de sesión.
 */
@Service
public class ServicioAutenticacion {

    private final RepositorioUsuarios repositorioUsuarios;
    private final PasswordEncoder codificadorPassword;

    public ServicioAutenticacion(
            RepositorioUsuarios repositorioUsuarios,
            PasswordEncoder codificadorPassword) {

        this.repositorioUsuarios = repositorioUsuarios;
        this.codificadorPassword = codificadorPassword;
    }

    /**
     * Registra un usuario y protege su contraseña con BCrypt.
     */
    public Usuario registrar(SolicitudRegistro datos) {

        String nombreUsuario = datos.nombreUsuario().trim();

        // Evita registrar nombres de usuario duplicados.
        if (repositorioUsuarios.existsByNombreUsuario(nombreUsuario)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "El nombre de usuario ya está registrado."
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(nombreUsuario);

        // Se almacena el hash, nunca la contraseña original.
        usuario.setPasswordHash(
            codificadorPassword.encode(datos.password())
        );

        usuario.setActivo(true);

        return repositorioUsuarios.save(usuario);
    }

    /**
     * Comprueba la existencia del usuario y valida su contraseña.
     */
    public boolean autenticar(String nombreUsuario, String password) {

        return repositorioUsuarios
            .findByNombreUsuario(nombreUsuario.trim())
            .filter(Usuario::getActivo)
            .map(usuario ->
                codificadorPassword.matches(
                    password,
                    usuario.getPasswordHash()
                )
            )
            .orElse(false);
    }
}
