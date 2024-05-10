package DTO;

public class AmigoDTO {
	private Long idUsuario;
	private Long idAmigo;
	
	public AmigoDTO(Long idUsuario, Long idAmigo) {
		this.idUsuario = idUsuario;
		this.idAmigo = idAmigo;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public Long getIdAmigo() {
		return idAmigo;
	}
}
