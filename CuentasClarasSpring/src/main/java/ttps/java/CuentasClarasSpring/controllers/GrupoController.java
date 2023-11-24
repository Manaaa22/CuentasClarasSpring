package ttps.java.CuentasClarasSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.java.CuentasClarasSpring.model.Gasto;
import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.services.GrupoService;

@RestController
@RequestMapping("/grupo")     //deberia ir esto? me da error ("/users", produces = MediaType.APPLICATION_JSON_VALUE)

public class GrupoController {
	@Autowired
	private GrupoService grupoService;
	
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
	public ResponseEntity<List<Gasto>> ListarGastosDeGrupo(@PathVariable("id") long id) {
		Grupo grupo = grupoService.recuperarPorId(id);
		if (grupo == null) {
			return new ResponseEntity<List<Gasto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Gasto>>(grupo.getGastos(), HttpStatus.OK);
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
			
			//y todos los demas setters?
		
			grupoService.actualizar(currentGrupo);
			return new ResponseEntity<Grupo>(currentGrupo, HttpStatus.OK);
		}
}
