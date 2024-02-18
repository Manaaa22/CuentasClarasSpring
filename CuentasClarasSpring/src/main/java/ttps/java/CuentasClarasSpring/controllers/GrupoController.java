package ttps.java.CuentasClarasSpring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.java.CuentasClarasSpring.model.Gasto;
import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.services.GrupoService;
import ttps.java.CuentasClarasSpring.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/grupo")     //deberia ir esto? me da error ("/users", produces = MediaType.APPLICATION_JSON_VALUE)

public class GrupoController {
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private UsuarioService usuarioService;
	
	 //Creo un grupo
	@PostMapping("/crearGrupo")
	public ResponseEntity<Grupo> crearGrupo(@RequestBody Grupo grupo) {	
		Grupo nuevoGrupo = grupoService.crear(grupo);
		return new ResponseEntity<Grupo>(nuevoGrupo, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Grupo>> recuperarTodos(){
		return new ResponseEntity<List<Grupo>> (grupoService.recuperarTodos(), HttpStatus.OK);
	}
	
	//Listado de todos los gastos de un grupo
	@GetMapping("/{id}")
	public ResponseEntity<Grupo> recuperarPorId(@PathVariable("id") long id) {
		Grupo grupo = grupoService.recuperarPorId(id);
		System.out.print(grupo.getNombre());
		if (grupo == null) {
			return new ResponseEntity<Grupo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
	}

	// Actualizo de los datos de un grupo
		@PutMapping("/{id}")
		public ResponseEntity<Grupo> actualizarGrupo(@PathVariable("id") long id, @RequestBody Grupo grupo) {
			System.out.println("Actualizando el grupo " + id);
			Grupo currentGrupo = grupoService.recuperarPorId(id);
			if (currentGrupo == null) {
				System.out.println("Grupo con id " + id + " not found");
				return new ResponseEntity<Grupo>(HttpStatus.NOT_FOUND);
			}
			currentGrupo.setNombre(grupo.getNombre());
			currentGrupo.setImagen(grupo.getImagen());
			currentGrupo.setCategoria(grupo.getCategoria());
			currentGrupo.setIntegrantes(null);
			
			//y todos los demas setters?
		
			grupoService.actualizar(currentGrupo);
			return new ResponseEntity<Grupo>(currentGrupo, HttpStatus.OK);
		}
		
		@PutMapping("/nuevoMiembro/{miembro}")
		public ResponseEntity<Grupo> agregarMiembro(@PathVariable("miembro") String miembro, @RequestBody Long id){
			Usuario usuario = this.usuarioService.recuperarPorUsuario(miembro);
			Grupo grupo = this.grupoService.recuperarPorId(id);
			
			if(grupoService.tieneIntegrante(usuario, id)) {
				return new ResponseEntity<Grupo>(grupo, HttpStatus.FOUND);
			}
			grupo.agregarIntegrante(usuario);
			Grupo gnuevo = grupoService.actualizar(grupo);
			Usuario unuevo = usuarioService.agregarUnGrupo(miembro, gnuevo);
			usuarioService.actualizar(unuevo);
			return new ResponseEntity<Grupo>(gnuevo, HttpStatus.OK);
		}
		
		@PutMapping("/borrarMiembro/{miembro}")
		public ResponseEntity<Grupo> borrarMiembro(@PathVariable("miembro") String miembro, @RequestBody Long id){
			Usuario usuario = this.usuarioService.recuperarPorUsuario(miembro);
			Grupo grupo = this.grupoService.recuperarPorId(id);
			if(grupoService.tieneIntegrante(usuario, grupo.getIdGrupo())) {
				grupoService.borrarMiembro(usuario, grupo.getIdGrupo());
				grupoService.actualizar(grupo);
				usuarioService.borrarUnGrupo(miembro, grupo);
				usuarioService.actualizar(usuario);
				return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
			}
			return new ResponseEntity<Grupo>(grupo, HttpStatus.NOT_FOUND);
		}
		
		@GetMapping("/{id}/miembros")
		public ResponseEntity<List<Usuario>> recuperarMiembrosPorId(@PathVariable("id") long id) {
			Grupo grupo = grupoService.recuperarPorId(id);
			return new ResponseEntity<List<Usuario>>(grupo.getIntegrantes(), HttpStatus.OK);
		}
		
		@PutMapping("/agregarGasto")
		public ResponseEntity<Grupo> agregarGasto(@RequestBody Grupo grupo, Gasto gasto){
			grupoService.agregarGasto(grupo, gasto);
			return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
		}
		
}
