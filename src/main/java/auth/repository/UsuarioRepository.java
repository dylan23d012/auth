
package auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auth.model.Usuario;

/**
 * Permite consultar y guardar usuarios en la base de datos.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su nombre de usuario.
    Optional<Usuario> findByUsername(String username);

    // Comprueba si el nombre de usuario ya está registrado.
    boolean existsByUsername(String username);
}
