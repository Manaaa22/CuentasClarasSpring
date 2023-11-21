package ttps.java.CuentasClarasSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.java.CuentasClarasSpring.model.Pago;
import ttps.java.CuentasClarasSpring.repository.PagoRepository;

@Service
//@Transactional
public class PagoService  {
	@Autowired
	private PagoRepository pagoRepository;
	
	public Pago actualizar(Pago pago) {
		// validaciones
		return pagoRepository.save(pago);
	}
	
	public boolean existe(Long id) {
		return pagoRepository.existsById(id);
	}
	
	public boolean existeEntidad(Pago pago) {
		return pagoRepository.existsById(pago.getIdPago());
	}
	
	public void eliminarConId(Long id) {
		pagoRepository.deleteById(id);
	}
	
	public void eliminarConEntidad(Pago pago) {
		pagoRepository.delete(pago);
	}
	
	public void eliminarTodos() {
		pagoRepository.deleteAll();
	}
	
	public List<Pago> recuperarTodos(){
		return pagoRepository.findAll();
		
	}
	
	public Pago recuperarPorId(Long id) {
		Optional<Pago> optionalPago = pagoRepository.findById(id);
	    return optionalPago.orElse(null);
	}

}
