package ttps.java.CuentasClarasSpring.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.repository.GrupoRepository;
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
	
	public boolean existeUsuario(Usuario usuario) {  //
		return usuarioRepository.existsByUsuario(usuario.getUsuario());
		}
	
	public boolean existeUsuarioContrasenia(String usuario, String contrasenia) {
		return usuarioRepository.existsByUsuarioAndContrasenia(usuario, contrasenia);
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


	public List<Grupo> recuperarGrupos(Long id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		Optional<List<Grupo>> grupos = Optional.of(usuario.get().getGrupos());
		return grupos.orElse(null);
	}
	
	public List<Grupo> recuperarGruposPorUsuario(String nombreDeUsuario){
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(nombreDeUsuario);
		Optional<List<Grupo>> grupos = Optional.of(usuario.get().getGrupos());
		return grupos.orElse(null);
	}
	
	public Usuario recuperarPorUsuario(String usuario){
		Optional<Usuario> optionalUsuario = usuarioRepository.findByUsuario(usuario);
	    return optionalUsuario.orElse(null);
		
	}
	
	public Usuario agregarUnGrupo(String usuario, Grupo grupo) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByUsuario(usuario);
		Usuario user = optionalUsuario.get();
		user.agregarUnGrupo(grupo);
		return user;
	}
	
	public void borrarUnGrupo(String usuario, Grupo grupo) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByUsuario(usuario);
		optionalUsuario.get().borrarUnGrupo(grupo);
	}
	
}