package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componentes.Usuario;
import repository.UsuarioRepository;

@Service
//@Transactional
public class UsuarioService extends GenericService<Usuario>{
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void setNuevaContrasenia(Usuario usuario, String nuevaContrasenia) {
		usuario.setContrasenia(nuevaContrasenia);
		usuarioRepository.save(usuario);
	}

		
}

