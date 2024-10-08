package DTO;

public class UsuarioDTO {
	private Long idUsuario;
	private String usuario;  
	private String nombre; 
	private String contrasenia;
	private String email;
	private String foto;
	
	public UsuarioDTO(Long idUsuario, String usuario, String nombre, String contrasenia, String email, String foto) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.email = email;
		this.foto = foto;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public String getEmail() {
		return email;
	}
	public String getFoto() {
		return foto;
	}
	
}
