package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componentes.Grupo;
import repository.GrupoRepository;

@Service
//@Transactional
public class GrupoService extends GenericService<Grupo> {
	@Autowired
	private GrupoRepository grupoRepository;
}
