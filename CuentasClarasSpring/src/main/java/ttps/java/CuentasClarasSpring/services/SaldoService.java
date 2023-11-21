package ttps.java.CuentasClarasSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.java.CuentasClarasSpring.model.Saldo;
import ttps.java.CuentasClarasSpring.repository.SaldoRepository;

@Service
//@Transactional
public class SaldoService{
	@Autowired
	private SaldoRepository saldoRepository;
	
	public Saldo actualizar(Saldo saldo) {
		// validaciones
		return saldoRepository.save(saldo);
	}
	
	public boolean existe(Long id) {
		return saldoRepository.existsById(id);
	}
	
	public boolean existeEntidad(Saldo saldo) {
		return saldoRepository.existsById(saldo.getIdSaldo());
	}
	
	public void eliminarConId(Long id) {
		saldoRepository.deleteById(id);
	}
	
	public void eliminarConEntidad(Saldo saldo) {
		saldoRepository.delete(saldo);
	}
	
	public void eliminarTodos() {
		saldoRepository.deleteAll();
	}
	
	public List<Saldo> recuperarTodos(){
		return saldoRepository.findAll();
		
	}
	
	public Saldo recuperarPorId(Long id) {
		Optional<Saldo> optionalSaldo = saldoRepository.findById(id);
	    return optionalSaldo.orElse(null);
	}

}
