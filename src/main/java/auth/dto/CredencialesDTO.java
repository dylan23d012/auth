
package auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Recibe las credenciales enviadas por el usuario.
 */
public class CredencialesDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50, message = "El usuario no puede superar 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 100, message = "La contraseña no puede superar 100 caracteres")
    private String password;

    public CredencialesDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
