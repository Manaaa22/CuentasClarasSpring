package com.example.CuentasClarasSpring.componentes;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name="Saldos")
public class Saldo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSaldo;
	private BigDecimal monto;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Gasto gasto;
	
	public Saldo() {
		super();
	}
	
	public Saldo(BigDecimal monto, Usuario usuario, Gasto gasto) {
		this.setMonto(monto);
		this.setUsuario(usuario);
		this.gasto=gasto;
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
