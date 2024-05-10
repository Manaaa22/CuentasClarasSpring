package DTO;

import ttps.java.CuentasClarasSpring.model.Categoria;

public class GrupoDTO {
	private Long idGrupo;
	private String nombre;
	private String imagen;
	private Categoria categoria;
	
	public GrupoDTO(Long idGrupo, String nombre, String imagen, Categoria categoria) {
		super();
		this.idGrupo = idGrupo;
		this.nombre = nombre;
		this.imagen = imagen;
		this.categoria = categoria;
	}

	public Long getIdGrupo() {
		return idGrupo;
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

}
