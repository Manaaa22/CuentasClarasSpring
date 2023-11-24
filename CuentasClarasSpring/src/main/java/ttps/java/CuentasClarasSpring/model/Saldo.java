package ttps.java.CuentasClarasSpring.model;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name="Saldos")
public class Saldo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSaldo")
	private Long idSaldo;
	private BigDecimal monto;
	@ManyToOne
	private Usuario usuario;
	
	public Saldo() {
		super();
	}
	
	public Saldo(BigDecimal bigDecimal, Usuario usuario) {
		this.setMonto(bigDecimal);
		this.setUsuario(usuario);
	}


	public Long getIdSaldo() {
		return idSaldo;
	}
	
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
