package ttps.java.CuentasClarasSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import ttps.java.CuentasClarasSpring.model.Usuario;
import ttps.java.CuentasClarasSpring.repository.*;
@Service
public class SeederService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private GrupoRepository grupoRepository;
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private GastoRepository gastoRepository;
	@Autowired
	private GastoService gastoService;
	@Autowired
	private PagoRepository pagoRepository;
	@Autowired
	private PagoService pagoService;
	@Autowired
	private SaldoRepository saldoRepository;
	@Autowired
	private SaldoService saldooService;
	
	@Value("${app.seedData}")
	private boolean wantToSeed;
	
    @PostConstruct
    public void init() {
        
        if(wantToSeed) {
        	System.out.println("Seeding...");
        	
        	usuarioRepository.deleteAll();
        	grupoRepository.deleteAll();
        	categoriaRepository.deleteAll();
        	gastoRepository.deleteAll();;
        	pagoRepository.deleteAll();
        	saldoRepository.deleteAll();
        	
        	Usuario u = new Usuario("link", "Link", "password123", "link@example.com", "foto1.jpg");
        	Usuario u1 = new Usuario("midna", "Midna", "password123", "midna@example.com", "foto2.jpg");
        	u = usuarioService.actualizar(u);
        	u1 = usuarioService.actualizar(u1);
        	usuarioService.agregarAmigo("link", u1.getIdUsuario());
        	
        }
    }
}
