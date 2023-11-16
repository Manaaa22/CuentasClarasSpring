package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componentes.Pago;
import repository.PagoRepository;

@Service
//@Transactional
public class PagoService extends GenericService<Pago> {
	@Autowired
	private PagoRepository pagoRepository;

}
