package com.example.CuentasClarasSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CuentasClarasSpring.componentes.Categoria;
import com.example.CuentasClarasSpring.componentes.Categoria;
import com.example.CuentasClarasSpring.componentes.Categoria;
import com.example.CuentasClarasSpring.repository.CategoriaRepository;

@Service
//@Transactional
public class CategoriaService  {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria actualizar(Categoria categoria) {
		// validaciones
		return categoriaRepository.save(categoria);
	}
	
	public boolean existe(Long id) {
		return categoriaRepository.existsById(id);
	}
	
	public boolean existeEntidad(Categoria categoria) {
		return categoriaRepository.existsById(categoria.getIdCategoria());
	}
	
	public void eliminarConId(Long id) {
		categoriaRepository.deleteById(id);
	}
	
	public void eliminarConEntidad(Categoria categoria) {
		categoriaRepository.delete(categoria);
	}
	
	public void eliminarTodos() {
		categoriaRepository.deleteAll();
	}
	
	public List<Categoria> recuperarTodos(){
		return categoriaRepository.findAll();
		
	}
	
	public Categoria recuperarPorId(Long id) {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
	    return optionalCategoria.orElse(null);
	}


}
