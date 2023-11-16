package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componentes.Saldo;
import repository.SaldoRepository;

@Service
//@Transactional
public class SaldoService extends GenericService<Saldo> {
	@Autowired
	private SaldoRepository saldoRepository;

}
