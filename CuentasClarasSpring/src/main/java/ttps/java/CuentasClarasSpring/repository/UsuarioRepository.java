package ttps.java.CuentasClarasSpring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.java.CuentasClarasSpring.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {  


}
