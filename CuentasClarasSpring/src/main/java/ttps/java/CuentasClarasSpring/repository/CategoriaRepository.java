package ttps.java.CuentasClarasSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ttps.java.CuentasClarasSpring.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {
	Categoria findByNombre(String nombre); 
}
