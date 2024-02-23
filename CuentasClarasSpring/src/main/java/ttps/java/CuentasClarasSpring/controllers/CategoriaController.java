package ttps.java.CuentasClarasSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.java.CuentasClarasSpring.model.Categoria;
import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.services.CategoriaService;

@CrossOrigin
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService catService;
	
	@PostMapping("/crearCategoria")
	public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria){
		Categoria cat = catService.crear(categoria);
		return new ResponseEntity<Categoria>(cat, HttpStatus.CREATED);
	}
	
	@GetMapping("/categoriaNombre/{nombre}")
	public ResponseEntity<Categoria> getByNombre(@PathVariable("nombre") String nombre) {
		System.out.println("Obteniendo categoria con nombre " + nombre);
		Categoria cat = catService.recuperarPorNombre(nombre);
		if (cat == null) {
			System.out.println("Categoria con nombre " + nombre + " no encontrada");
			return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
	}
	
	
	@GetMapping("/categoriaId/{id}")
	public ResponseEntity<Categoria> recuperarPorId(@PathVariable("id") long id){
		Categoria cat = catService.recuperarPorId(id);
		System.out.print(cat.getNombre());
		return new ResponseEntity<Categoria> (cat, HttpStatus.OK);
	}
	@GetMapping()
	public ResponseEntity<List<Categoria>> recuperarCategorias() {
		List<Categoria> cat = catService.recuperarTodos();
		
		return new ResponseEntity<List<Categoria>>(cat, HttpStatus.OK);

	}
}
