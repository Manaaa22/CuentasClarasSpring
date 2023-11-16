package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componentes.Gasto;
import repository.GastoRepository;

@Service
//@Transactional
public class GastoService extends GenericService<Gasto> {
	@Autowired
	private GastoRepository gastoRepository;
}
