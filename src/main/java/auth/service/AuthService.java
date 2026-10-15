
package auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auth.dto.CredencialesDTO;
import auth.model.Usuario;
import auth.repository.UsuarioRepository;

/**
 * Contiene la lógica para registrar y autenticar usuarios.
 */
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registra un usuario almacenando su contraseña cifrada.
     */
    @Transactional
    public String registrar(CredencialesDTO credenciales) {
        String username = credenciales.getUsername().trim();
        String password = credenciales.getPassword();

        if (usuarioRepository.existsByUsername(username)) {
            throw new IllegalArgumentException(
                "El nombre de usuario ya existe"
            );
        }

        Usuario usuario = new Usuario(
            username,
            passwordEncoder.encode(password)
        );

        usuarioRepository.save(usuario);

        return "Usuario registrado correctamente";
    }

    /**
     * Comprueba las credenciales sin revelar cuál dato es incorrecto.
     */
    @Transactional(readOnly = true)
    public boolean autenticar(CredencialesDTO credenciales) {
        String username = credenciales.getUsername().trim();

        return usuarioRepository.findByUsername(username)
            .map(usuario -> passwordEncoder.matches(
                credenciales.getPassword(),
                usuario.getPassword()
            ))
            .orElse(false);
    }
}
