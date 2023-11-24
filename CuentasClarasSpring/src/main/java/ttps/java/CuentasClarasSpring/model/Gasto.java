package ttps.java.CuentasClarasSpring.model;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name="Gastos")
public class Gasto {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGasto;
	private BigDecimal monto;
	private Calendar fecha;
	private Integer tipoDivision;
	@ManyToOne
	@JoinColumn(referencedColumnName="idCategoria")
	private Categoria categoria;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName="idGrupo")
	private Grupo grupo;
	@ManyToOne
	@JoinColumn(referencedColumnName="idUsuario")
	private Usuario usuario;
	@OneToMany
	@JoinColumn(name="idSaldo")
	private List<Saldo> saldos;
	private String imagen;
	
	public Gasto() {
		super();
	}
	
	public Gasto(BigDecimal monto, Calendar fecha, Integer tipoDivision, Categoria categoria, Grupo grupo,
			Usuario usuario, List<Saldo> saldos, String imagen) {
		this.monto = monto;
		this.fecha = fecha;
		this.tipoDivision = tipoDivision;
		this.categoria = categoria;
		this.grupo = grupo;
		this.usuario = usuario;
		this.saldos = saldos;
		this.imagen = imagen;
	}
	public Long getIdGasto() {
		return idGasto;
	}
	public void setIdGasto(Long idGasto) {
		this.idGasto = idGasto;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public Integer getTipoDivision() {
		return tipoDivision;
	}
	public void setTipoDivision(Integer tipoDivision) {
		this.tipoDivision = tipoDivision;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
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
	public List<Saldo> getSaldos() {
		return saldos;
	}
	public void setSaldos(List<Saldo> saldos) {
		this.saldos = saldos;
	}
	
	public void calcularDivision() {
		//si tipoDivision = 1, llama a calcularTodosIgual(), si es 2 a calcularMOntoFIjo() y si es 3 a calcularDiferentesPorcentajes()
	}
	
	public void calcularTodosIgual() {
		//divide el monto por la cantidad de integrantes o por 2 si es gasto sin grupo y afecta los saldos correspondientes
	}

	public void calcularMontoFijo() {
		//toma los montos que dio el usuario y afecta los saldos correspondientes
	}
	
	public void calcularDiferentesPorcentajes() {
		//calcula los diferentes porcentajes con la cantidad de miembros o por dos si es sin grupo y afecta los saldos correspondientes
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
