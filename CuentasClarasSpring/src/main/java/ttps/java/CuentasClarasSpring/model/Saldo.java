package ttps.java.CuentasClarasSpring.model;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="Saldos")
public class Saldo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSaldo")
	private Long idSaldo;
	private Double monto;
	@JsonIgnore
	@ManyToOne
	private Usuario usuario;
	
	public Saldo() {
		super();
	}
	
	public Saldo(Double monto, Usuario usuario) {
		this.setMonto(monto);
		this.setUsuario(usuario);
	}


	public Long getIdSaldo() {
		return idSaldo;
	}
	
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
