package com.example.CuentasClarasSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CuentasClarasSpring.componentes.Gasto;
import com.example.CuentasClarasSpring.componentes.Usuario;
import com.example.CuentasClarasSpring.services.GastoService;

@RestController
@RequestMapping("/gasto")
public class GastoController {
	@Autowired
	private GastoService gastoService;
	
	
	//alta de una gasto
	
	
	//edicion de un gasto
	@PutMapping("/{id}")
	public ResponseEntity<Gasto> actualizarUsuario(@PathVariable("id") long id, @RequestBody Gasto gasto) {
		System.out.println("Actualizando el gasto " + id);
		Gasto currentGasto = gastoService.recuperarPorId(id);
		if (currentGasto == null) {
			System.out.println("Gasto con id " + id + " not found");
			return new ResponseEntity<Gasto>(HttpStatus.NOT_FOUND);
		}
		currentGasto.setMonto(gasto.getMonto());
		currentGasto.setTipoDivision(gasto.getTipoDivision());
		currentGasto.setCategoria(gasto.getCategoria());

		// y todos los demas setters?

		gastoService.actualizar(currentGasto);
		return new ResponseEntity<Gasto>(currentGasto, HttpStatus.OK);
	}
}
