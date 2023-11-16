package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.GenericRepository;

@Service
//@Transactional
public class GenericService<T> {
	@Autowired
	private GenericRepository<T> genericRepository;

	public T actualizar(T t) {
		// validaciones
		return genericRepository.save(t);
	}
	
	public boolean existe(Long id) {
		return genericRepository.exists(id);
	}
	
	public void eliminarConId(Long id) {
		genericRepository.deleteById(id);
	}
	
	public void eliminarConEntidad(T t) {
		genericRepository.delete(t);
	}
	
	public void eliminarTodos() {
		genericRepository.deleteAll();
	}
	
	public List<T> recuperarTodos(){
		return genericRepository.findAll();
		
	}
	
	public T recuperarPorId(Long id) {
		return genericRepository.findOne(id);
	}
}
