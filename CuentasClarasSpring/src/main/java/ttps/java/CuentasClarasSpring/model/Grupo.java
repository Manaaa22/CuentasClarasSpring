package ttps.java.CuentasClarasSpring.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name="Grupos")
public class Grupo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupo;
	private String nombre;
	private String imagen;
	
	@ManyToOne(cascade= CascadeType.MERGE)
	@JoinColumn(referencedColumnName="idCategoria")
	private Categoria categoria;
	
	@JsonIgnore
	@OneToMany
	@JoinColumn(name="idGrupo")
	private List<Saldo> saldos;
	
	@JsonIgnore
	@ManyToMany(mappedBy="grupos")
	private List<Usuario> integrantes;
	
	@JsonIgnore
	@OneToMany(mappedBy="grupo")
	private List<Gasto> gastos;
	
	@JsonIgnore
	@OneToMany(mappedBy="grupo")
	private List<Pago> pagos;

	public Grupo() {
		super();
	}
 
 public Grupo(String nombre, String imagen, Categoria categoria,
		List<Usuario> integrantes) {
	this.nombre = nombre;
	this.imagen = imagen;
	this.categoria = categoria;
	this.saldos = new ArrayList<Saldo>();
	this.integrantes= integrantes;
	this.gastos = new ArrayList<Gasto>();
	this.pagos = new ArrayList<Pago>();
}

public Long getIdGrupo() {
	return idGrupo;
}

public void setIdGrupo(Long idGrupo) { this.idGrupo = idGrupo; }

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getImagen() {
	return imagen;
}
public void setImagen(String imagen) {
	this.imagen = imagen;
}
public Categoria getCategoria() {
	return categoria;
}
public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}
public List<Saldo> getSaldos() {
	return saldos;
}
public void setSaldos(List<Saldo> saldos) {
	this.saldos = saldos;
}
public List<Usuario> getIntegrantes() {
	return this.integrantes;
}

public void agregarIntegrante(Usuario usuario) {
	this.getIntegrantes().add(usuario);
}
public void eliminarIntegrante(Usuario usuario) {
	if(this.getIntegrantes().contains(usuario)) {
		this.getIntegrantes().remove(usuario);
	}
}
public void setIntegrantes(List<Usuario> integrantes) {
	this.integrantes = integrantes;
}
public List<Gasto> getGastos() {
	return gastos;
}
public void setGastos(List<Gasto> gastos) {
	this.gastos = gastos;
}
public List<Pago> getPagos() {
	return pagos;
}
public void setPagos(List<Pago> pagos) {
	this.pagos = pagos;
}
public void agregarGasto(Gasto gasto) {
	this.getGastos().add(gasto);
}

}
