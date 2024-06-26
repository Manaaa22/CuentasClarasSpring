package ttps.java.CuentasClarasSpring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.java.CuentasClarasSpring.model.Gasto;
import ttps.java.CuentasClarasSpring.model.Grupo;
import ttps.java.CuentasClarasSpring.model.Saldo;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.repository.GastoRepository;

@Service
//@Transactional
public class GastoService  {
	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private SaldoService saldoService;
	
	public Gasto crear(Grupo grupo, Gasto gasto) {
		List<Saldo> saldos = new ArrayList<Saldo>();
		List<Usuario> miembros = grupo.getIntegrantes();
		Integer cant = miembros.size();
		if (gasto.getTipoDivision().equals(1)) { //dividir de forma igual
			Double montoDiv = gasto.getMonto()/cant;
			Saldo s;
			for(int i=0;i<cant;i++) {
				if(miembros.get(i).getIdUsuario().equals(gasto.getUsuario().getIdUsuario())) {
					System.out.println(miembros.get(i).getNombre());
					s = new Saldo(montoDiv,miembros.get(i)); //se asume que el usuario creador pago
				} else {
					s = new Saldo(-montoDiv,miembros.get(i));
				}
				saldoService.crear(s);
				System.out.println(s.getIdSaldo());
				saldos.add(s);
			}
		}
		gasto.setSaldos(saldos);
		System.out.println("llegue a gasto service");
		return gastoRepository.save(gasto);
	}
	
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
