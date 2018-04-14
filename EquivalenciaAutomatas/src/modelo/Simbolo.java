package modelo;

public class Simbolo {

	/**
	 * Identificador del simbolo
	 */
	private String id;

	
	/**
	 * Constructor
	 * @param id
	 */
	public Simbolo(String id) {

		this.id = id;
	}

	/**
	 * retorna el simbolo
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * permite modificar el simbolo
	 * @param id
	 */

	public void setId(String id) {
		this.id = id;
	}
	

}
