
package com.example.CuentasClarasSpring.componentes;
import jakarta.persistence.*;

@Entity
@Table(name="Categorias")
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	private String tipo;
	private String imagen;
	
	public Categoria() {
		super();
	}
	
	public Categoria(String tipo, String imagen) {
		this.tipo = tipo;
		this.imagen = imagen;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}
