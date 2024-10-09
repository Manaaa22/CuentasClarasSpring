package DTO;

import java.util.List;

import ttps.java.CuentasClarasSpring.model.Categoria;
import ttps.java.CuentasClarasSpring.model.Usuario;

public class GrupoDTO {
	private String nombre;
	
//	private List<Usuario> integrantes;
	private Long categoria;
	
	public GrupoDTO(String nombre, Long categoria, List<Usuario> integrantes) {
		this.nombre = nombre;
		
		this.categoria = categoria;
//		this.integrantes=integrantes;
	}
	
	public GrupoDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}


	public Long getCategoria() {
		return categoria;
	}

//	public List<Usuario> getIntegrantes() {
//		return integrantes;
//	}

}
