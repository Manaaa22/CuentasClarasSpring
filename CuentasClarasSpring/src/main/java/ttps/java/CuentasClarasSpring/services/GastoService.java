package ttps.java.CuentasClarasSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.java.CuentasClarasSpring.model.Gasto;
import ttps.java.CuentasClarasSpring.repository.GastoRepository;

@Service
//@Transactional
public class GastoService  {
	@Autowired
	private GastoRepository gastoRepository;
	
	public Gasto actualizar(Gasto gasto) {
		// validaciones
		return gastoRepository.save(gasto);
	}
	
	public boolean existe(Long id) {
		return gastoRepository.existsById(id);
	}
	
	public boolean existeEntidad(Gasto gasto) {
		return gastoRepository.existsById(gasto.getIdGasto());
	}
	
	public void eliminarConId(Long id) {
		gastoRepository.deleteById(id);
	}
	
	public void eliminarConEntidad(Gasto gasto) {
		gastoRepository.delete(gasto);
	}
	
	public void eliminarTodos() {
		gastoRepository.deleteAll();
	}
	
	public List<Gasto> recuperarTodos(){
		return gastoRepository.findAll();
		
	}
	
	public Gasto recuperarPorId(Long id) {
		 Optional<Gasto> optionalGasto = gastoRepository.findById(id);
		    return optionalGasto.orElse(null);
	}

}
