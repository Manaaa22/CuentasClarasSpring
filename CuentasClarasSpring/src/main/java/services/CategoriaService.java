package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import componentes.Categoria;
import repository.CategoriaRepository;

@Service
//@Transactional
public class CategoriaService extends GenericService<Categoria> {
	@Autowired
	private CategoriaRepository categoriaRepository;

}
