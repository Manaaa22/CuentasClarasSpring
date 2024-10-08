package DTO;

import java.util.List;

import ttps.java.CuentasClarasSpring.model.Categoria;
import ttps.java.CuentasClarasSpring.model.Usuario;

public class GrupoDTO {
	private String nombre;
	private String imagen;
	private List<Usuario> integrantes;
	private Categoria categoria;
	
	public GrupoDTO(String nombre, String imagen, Categoria categoria, List<Usuario> integrantes) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.categoria = categoria;
		this.integrantes=integrantes;
	}
	
	public GrupoDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public List<Usuario> getIntegrantes() {
		return integrantes;
	}

}
