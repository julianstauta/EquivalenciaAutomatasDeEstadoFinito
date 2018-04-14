package modelo;

public class Respuesta {

	/**
	 * respuesta que entregara el sistema
	 */
	private String id;

	
	/**
	 * Constructor de la clase
	 * @param id Respuesta
	 */
	public Respuesta(String id) {
		this.id = id;
	}

	/**
	 * Retorna la respuesta
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Permite cambiar el identificador de la respuesta del sistema
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
