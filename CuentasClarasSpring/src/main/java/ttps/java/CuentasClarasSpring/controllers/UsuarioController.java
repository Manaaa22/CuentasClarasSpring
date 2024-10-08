package ttps.java.CuentasClarasSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.services.GastoService;
import ttps.java.CuentasClarasSpring.services.UsuarioService;
import ttps.java.CuentasClarasSpring.services.GrupoService;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private GrupoService grupoService;

	// Creo un usuario
	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		System.out.println("Creando el usuario " + usuario.getUsuario());
		if (usuarioService.existeUsuario(usuario)) {
			System.out.println("Ya existe un usuario con nombre " + usuario.getUsuario());
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT); // 409
		}
		usuarioService.actualizar(usuario);
		System.out.println("Se creo el usuario " + usuario.getUsuario());
		return new ResponseEntity<Usuario>(HttpStatus.CREATED); // 201
	}

	// login de usuario
	@PostMapping("/login")
	public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario usuario) {
		if (usuarioService.existeUsuarioContrasenia(usuario.getUsuario(), usuario.getContrasenia())) {
			Usuario usuarioLogueado = usuarioService.recuperarPorUsuario(usuario.getUsuario());
			System.out.println("Se logueo " + usuario.getUsuario());
			return ResponseEntity.accepted().body(usuarioLogueado); // 202
		}
		System.out.println("Usuario o contrasenia incorrectos");
		return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED); // 401
	}

	// Recupero todos los usuarios
	@GetMapping
	public ResponseEntity<List<Usuario>> listarTodosLosUsuarios() {
		List<Usuario> usuarios = usuarioService.recuperarTodos();
		if (usuarios.isEmpty()) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT); // 204
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // 200
	}

	// Recupero un usuario dado
	@GetMapping("/id/{id}")
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
		currentUsuario = usuario;
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

	@GetMapping("/{username}/grupos")
	public ResponseEntity<List<Grupo>> gruposDeUnUsuario(@PathVariable("username") String username) {
		System.out.println("llegue al back");
		List<Grupo> grupos = usuarioService.recuperarGruposPorUsuario(username);
		return new ResponseEntity<List<Grupo>>(grupos, HttpStatus.OK);
	}

	
	@GetMapping("/{username}/amigos")
	public ResponseEntity<List<Usuario>> amigosDeUnUsuario(@PathVariable("username") String username) {
		System.out.println("llegue aa get amigos");
		List<Usuario> amigos = usuarioService.recuperarAmigosDeUsuario(username);
		return new ResponseEntity<List<Usuario>>(amigos, HttpStatus.OK);
	}
	

// Recupero un usuario dado por username
	@GetMapping("/username/{username}")
	public ResponseEntity<Usuario> getUsuarioUsername(@PathVariable("username") String username) {
		System.out.println("Obteniendo usuario" + username);
		Usuario usuario = usuarioService.recuperarPorUsername(username);
		if (usuario == null) {
			System.out.println("Usuario" + username + " no encontrado");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PutMapping("/nuevoAmigo/{username}/{id}")
	public ResponseEntity<Usuario> agregarAmigo(@PathVariable("username") String username, @PathVariable("id") long id){
		Usuario usuario = usuarioService.recuperarPorUsername(username);
		if (usuario == null) {
			System.out.println("Usuario" + username + " no encontrado");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		usuarioService.agregarAmigo(username, id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
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
