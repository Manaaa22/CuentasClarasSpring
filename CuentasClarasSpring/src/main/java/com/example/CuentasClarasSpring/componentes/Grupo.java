package com.example.CuentasClarasSpring.componentes;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="Grupos")
public class Grupo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGrupo;
	private String nombre;
	private String imagen;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idCategoria")
	private Categoria categoria;
	
	@OneToMany
	@JoinColumn(name="idSaldo")
	private List<Saldo> saldos;
	
	@ManyToMany(mappedBy="grupos")
	private List<Usuario> integrantes;
	
	@OneToMany(mappedBy="grupo")
	private List<Gasto> gastos;
	
	@OneToMany(mappedBy="grupo")
	private List<Pago> pagos;

	public Grupo() {
		super();
	}
 
 public Grupo(String nombre, String imagen, Categoria categoria, List<Saldo> saldos,
		List<Usuario> integrantes, List<Gasto> gastos, List<Pago> pagos) {
	this.nombre = nombre;
	this.imagen = imagen;
	this.categoria = categoria;
	this.saldos = saldos;
	this.integrantes = integrantes;
	this.gastos = gastos;
	this.pagos = pagos;
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
	return integrantes;
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
}
