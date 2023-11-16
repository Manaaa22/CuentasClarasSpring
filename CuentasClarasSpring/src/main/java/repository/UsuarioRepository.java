package repository;


import componentes.Usuario;

public interface UsuarioRepository extends GenericRepository<Usuario> {  
																			
	Usuario findByFirstName(String firstName);
	// @Query("SELECT u FROM Usuario u WHERE LOWER(u.nombre)=LOWER(?1)")
	// Usuario retrieveByFirstName(@Param("nombre") String nombre);
	
	//querys pag12.teoria8

}
