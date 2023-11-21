package ttps.java.CuentasClarasSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.repository.UsuarioRepository;

@Service
//@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void setNuevaContrasenia(Usuario usuario, String nuevaContrasenia) {
		usuario.setContrasenia(nuevaContrasenia);
		usuarioRepository.save(usuario);
	}
	
	public Usuario actualizar(Usuario usuario) {   //
		// validaciones
		return usuarioRepository.save(usuario);
	}
	
	public boolean existe(Long id) {
		return usuarioRepository.existsById(id);
	}
	
	public boolean existeEntidad(Usuario usuario) {  //
		return usuarioRepository.existsById(usuario.getIdUsuario());
	}
	
	public void eliminarConId(Long id) {  //
		usuarioRepository.deleteById(id);
	}
	
	public void eliminarConEntidad(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
	
	public void eliminarTodos() {
		usuarioRepository.deleteAll();    //
	}
	
	public List<Usuario> recuperarTodos(){ //
		return usuarioRepository.findAll();
		
	}
	
	public Usuario recuperarPorId(Long id) {   //
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
	    return optionalUsuario.orElse(null);
	}

		
}

