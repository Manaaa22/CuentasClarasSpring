package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import componentes.Usuario;
import services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	// Creo un usuario
	//@PostMapping
	//public ResponseEntity<Void> crearUsuario(@RequestBody Usuario usuario) {
	//System.out.println("Creando el usuario" + usuario.getNombre());
	//if (usuarioService.existeEntidad(usuario)) {
		//System.out.println("Ya existe un usuario con nombre " + usuario.getNombre());
		//return new ResponseEntity<Void>(HttpStatus.CONFLICT); //Código de respuesta 409  
	//}
	//usuarioService.actualizar(usuario);
	//return new ResponseEntity<Usuario>(headers, HttpStatus.CREATED);
	//}

	// Recupero todos los usuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> listarTodosLosUsuarios() {
		List<Usuario> usuarios = usuarioService.recuperarTodos();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}

	// Recupero un usuario dado
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") long id) {
		System.out.println("Obteniendo usuario con id " + id);
		Usuario usuario = usuarioService.recuperarPorId(id);
		if (usuario == null) {
			System.out.println("Usuario con id " + id + " no encontrado");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	// Actualizo un usuario
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
		System.out.println("Actualizando el usuario " + id);
		Usuario currentUsuario = usuarioService.recuperarPorId(id);
		if (currentUsuario == null) {
			System.out.println("Usuario con id " + id + " not found");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		currentUsuario.setNombre(usuario.getNombre());

		// y todos los demas setters

		usuarioService.actualizar(currentUsuario);
		return new ResponseEntity<Usuario>(currentUsuario, HttpStatus.OK);
	}

	// Elimino un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id") long id) {
		System.out.println("Obteniendo y eliminando el usuario con id " + id);
		Usuario user = usuarioService.recuperarPorId(id);
		if (user == null) {
			System.out.println("No es posible eliminar, no se encuentra el usuario con id " + id);
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		usuarioService.eliminarConId(id);
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}

	// Elimino todos los usuarios
	@DeleteMapping
	public ResponseEntity<Usuario> eliminarTodosLosUsuarios() {
		System.out.println("Eliminando todos los usuarios");
		usuarioService.eliminarTodos();
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
	}
}
// @PutMapping(value = "/{id}")
// public Usuario update(@RequestBody Usuario usuario, @PathVariable("id") Long
// id) {
// usuario.setId(Long id);
// return this.usuarioService.update(usuario);
// }

//@PostMapping
// public Usuario create(@RequestBody Usuario usuario) {
// validaciones
// return this.usuarioService.crear(usuario);
// }
