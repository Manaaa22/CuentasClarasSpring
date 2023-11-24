package ttps.java.CuentasClarasSpring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ttps.java.CuentasClarasSpring.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {  

boolean existsByUsuario(String usuario);

boolean existsByUsuarioAndContrasenia(String usuario, String contrasenia);

}