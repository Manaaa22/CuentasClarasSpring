package ttps.java.CuentasClarasSpring.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="Pagos")
public class Pago {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPago;
	private Double monto;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(referencedColumnName="idGrupo")
	private Grupo grupo;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(referencedColumnName="idUsuario")
	private Usuario usuario;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(referencedColumnName="idUsuario")
	private Usuario usuarioDestino;
	
	public Pago() {
		super();
	}
	
	public Pago (Double monto, Grupo grupo, Usuario usuario, Usuario usuarioDestino) {
		this.monto = monto;
		this.grupo = grupo;
		this.usuario = usuario;
		this.usuarioDestino = usuarioDestino;
	}
	
	
	public Long getIdPago() {
		return idPago;
	}
	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}
	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}
	
	public void efectuarPago() {
		//reduce deudas en saldos correspondientes
	}
	
	public Boolean tieneGrupo() {
		if(this.getGrupo()!=null) {
			return true;
		}
		else {
			return false;
		}
	}
}
