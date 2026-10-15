
package auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MethodArgumentNotValidException;

import auth.dto.CredencialesDTO;
import auth.service.AuthService;

/**
 * Expone los endpoints REST de registro e inicio de sesión.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registra un usuario nuevo.
     */
    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registrar(
            @Valid @RequestBody CredencialesDTO credenciales) {

        String mensaje = authService.registrar(credenciales);

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", mensaje);

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    /**
     * Comprueba las credenciales del usuario.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody CredencialesDTO credenciales) {

        Map<String, String> respuesta = new HashMap<>();

        if (authService.autenticar(credenciales)) {
            respuesta.put("mensaje", "Autenticación satisfactoria");
            return ResponseEntity.ok(respuesta);
        }

        respuesta.put("mensaje", "Error en la autenticación");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
    }

    /**
     * Responde cuando se intenta registrar un nombre de usuario existente.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> manejarDatoDuplicado(
            IllegalArgumentException excepcion) {

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", excepcion.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
    }

    /**
     * Devuelve un error claro cuando faltan datos obligatorios.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidacion(
            MethodArgumentNotValidException excepcion) {

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "El usuario y la contraseña son obligatorios");

        return ResponseEntity.badRequest().body(respuesta);
    }
}
