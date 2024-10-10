package ttps.java.CuentasClarasSpring.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import DTO.GrupoDTO;
import ttps.java.CuentasClarasSpring.model.Categoria;
import ttps.java.CuentasClarasSpring.model.Gasto;
import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.services.CategoriaService;
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
	@Autowired
	private CategoriaService categoriaService;

	
	 //Creo un grupo
	//@PostMapping("/crearGrupo")
	//public ResponseEntity<Grupo> crearGrupo(@RequestBody Grupo grupo) {	
	//	Grupo nuevoGrupo = grupoService.crear(grupo);
		//return new ResponseEntity<Grupo>(nuevoGrupo, HttpStatus.CREATED);
	//}
	
	@GetMapping
	public ResponseEntity<List<Grupo>> recuperarTodos(){
		return new ResponseEntity<List<Grupo>> (grupoService.recuperarTodos(), HttpStatus.OK);
	}
	
	@PostMapping("/{username}/crearGrupo")
	public ResponseEntity<Object> crearGrupo(@RequestBody GrupoDTO grupoDTO, @PathVariable("username") String username) {
	    Map<String, String> errorResponse = new HashMap<>();
		
	    if (username == null) {
	    	errorResponse.put("message", "El usuario no puede ser null");
	        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	    }
	    Usuario usuario = usuarioService.recuperarPorUsername(username);
	    
		
		if (grupoDTO.getCategoria() == null) {
			errorResponse.put("message", "la categoría no puede ser null");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}
	    Categoria categoria = categoriaService.recuperarPorId(grupoDTO.getCategoria());
	    
	    int[] idsIntegrantes = grupoDTO.getIntegrantes();
	    System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	    System.out.print(idsIntegrantes);
	    if(idsIntegrantes == null || idsIntegrantes.length==0) {
	    	errorResponse.put("message", "los integrantes no pueden ser null ni estar vacia");
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	    List<Usuario> integrantes = new ArrayList<Usuario>();
	    for (int i=0;i<idsIntegrantes.length;i++) {
	    	Usuario u = usuarioService.recuperarPorId((long) idsIntegrantes[i]);
	    	integrantes.add(u);
	    }
	    integrantes.add(usuario);
	    Grupo grupoNuevo = new Grupo();
	    grupoNuevo.setCategoria(categoria);
	    grupoNuevo.setIntegrantes(integrantes);
	    grupoNuevo.setNombre(grupoDTO.getNombre());
	    
	    grupoNuevo = grupoService.crear(grupoNuevo);
	    for (Usuario i : integrantes) {
	    	usuarioService.agregarUnGrupo(i.getUsuario(), grupoNuevo);
	    }
	    usuarioService.actualizar(usuario);

	    return new ResponseEntity<>(grupoNuevo, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Grupo> recuperarPorId(@PathVariable("id") long id) { //WTF??
		Grupo grupo = grupoService.recuperarPorId(id);
		System.out.print(grupo.getNombre());
		if (grupo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Grupo>(grupo, HttpStatus.OK);
	}

	// Actualizo de los datos de un grupo
		@PutMapping("/{id}")
		public ResponseEntity<Grupo> actualizarGrupo(@PathVariable("id") long id, @RequestBody GrupoDTO grupoDTO) {
			System.out.println("Actualizando el grupo " + id);
			Grupo currentGrupo = grupoService.recuperarPorId(id);
			if (currentGrupo == null) {
				System.out.println("Grupo con id " + id + " no encontrado");
				return new ResponseEntity<Grupo>(HttpStatus.NOT_FOUND);
			}
			currentGrupo.setNombre(grupoDTO.getNombre());
			
			currentGrupo.setCategoria(categoriaService.recuperarPorId(grupoDTO.getCategoria()));
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
		

		@GetMapping("/{id}/gastos")
		public ResponseEntity<List<Gasto>> recuperarGastosPorId(@PathVariable("id") long id) {
			Grupo grupo = grupoService.recuperarPorId(id);
			
			return new ResponseEntity<List<Gasto>>(grupo.getGastos(), HttpStatus.OK);
		}
			

		@PostMapping("/{id}/agregarGasto")
		public ResponseEntity<Grupo> agregarGasto(@PathVariable("id") long id, @RequestBody Gasto gasto){
			Grupo grupo = grupoService.recuperarPorId(id);
			System.out.println("agregando gasto");
			Grupo grupoAct= new Grupo();
			grupoAct=grupoService.agregarGasto(grupo, gasto);
			return new ResponseEntity<Grupo>(grupoAct, HttpStatus.OK);
		}
		

}
