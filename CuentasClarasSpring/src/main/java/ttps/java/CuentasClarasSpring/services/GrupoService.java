package ttps.java.CuentasClarasSpring.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.java.CuentasClarasSpring.model.Gasto;
import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.model.Pago;
import ttps.java.CuentasClarasSpring.model.Saldo;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.repository.GrupoRepository;

@Service
//@Transactional
public class GrupoService  {
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private SaldoService saldoService;
	
	
	public Grupo crear(Grupo grupo) {
		List<Saldo> saldos = new ArrayList<Saldo>();
		for(Usuario user: grupo.getIntegrantes()) {
			Saldo s = new Saldo(BigDecimal.valueOf(0),user);
			saldos.add(saldoService.crear(s));
		}
		grupo.setSaldos(saldos);
		List<Gasto> gastos = new ArrayList<Gasto>();
		grupo.setGastos(gastos);
		List<Pago> pagos = new ArrayList<Pago>();
		grupo.setPagos(pagos);
		return grupoRepository.save(grupo);
	}
	
	public Grupo actualizar(Grupo grupo) {
		// validaciones
		return grupoRepository.save(grupo);
	}
	
	public boolean existe(Long id) {
		return grupoRepository.existsById(id);
	}
	
	public boolean existeEntidad(Grupo grupo) {
		return grupoRepository.existsById(grupo.getIdGrupo());
	}
	
	public void eliminarConId(Long id) {
		grupoRepository.deleteById(id);
	}
	
	public void eliminarConEntidad(Grupo grupo) {
		grupoRepository.delete(grupo);
	}
	
	public void eliminarTodos() {
		grupoRepository.deleteAll();
	}
	
	public List<Grupo> recuperarTodos(){
		return grupoRepository.findAll();
		
	}
	
	public Grupo recuperarPorId(Long id) {
		Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
	    return optionalGrupo.orElse(null);
	}

}
