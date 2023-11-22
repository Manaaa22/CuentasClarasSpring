
package ttps.java.CuentasClarasSpring.model;
import jakarta.persistence.*;

@Entity
@Table(name="categorias")
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	private String tipo;
	private String imagen;
	private String nombre;
	
	public Categoria() {
		super();
	}
	
	public Categoria(String nombre,String tipo, String imagen) {
		this.setNombre(nombre);
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
