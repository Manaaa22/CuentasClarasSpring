package ttps.java.CuentasClarasSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.CuentasClarasSpring.model.Credentials;
import ttps.java.CuentasClarasSpring.model.UsernaneAndPassword;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.services.TokenServices;
import ttps.java.CuentasClarasSpring.services.UsuarioService;

/**
 * @author manuel
 */
@RestController
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenServices tokenServices;

    // un dia
    private final int EXPIRATION_IN_SEC = 100;

    @PostMapping(path = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody UsernaneAndPassword userpass) {

        if(isLoginSuccess(userpass.getUsername(), userpass.getPassword())) {
            String token = tokenServices.generateToken(userpass.getUsername(), EXPIRATION_IN_SEC);
            return ResponseEntity.ok(new Credentials(token, EXPIRATION_IN_SEC, userpass.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o password incorrecto");
        }
    }

    private boolean isLoginSuccess(String username, String password) {
        // recupero el usuario de la base de usuarios
        Usuario u = usuarioService.recuperarPorUsuario(username);

        // chequeo que el usuario exista y el password sea correcto
        return (u != null && u.getContrasenia().equals(password));
    }
}
