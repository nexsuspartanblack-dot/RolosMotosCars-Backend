
package com.rolosmotoscars.backend.repositorio;

import com.rolosmotoscars.backend.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Permite consultar, registrar y administrar usuarios en MySQL.
 */
public interface RepositorioUsuarios extends JpaRepository<Usuario, Integer> {

    // Busca un usuario mediante su nombre de usuario.
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    // Comprueba si el nombre de usuario ya está registrado.
    boolean existsByNombreUsuario(String nombreUsuario);
}
