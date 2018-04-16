package modelo;

public class TransicionMealy {

	/**
	 * Estado final de la transicion
	 */
	private EstadoMealy estadoFinal;
	/**
	 * Simbolo que genera la transicion
	 */
	private Respuesta respuesta;
	
	
	/**
	 * Constructor de la clase trancision
	 * @param estadoInicial
	 * @param estadoFinal
	 * @param simbolo
	 */
	public TransicionMealy(EstadoMealy estadoFinal, Respuesta respuesta) {
		
		this.estadoFinal = estadoFinal;
		this.respuesta=respuesta;
	}


	/**
	 * retorna el estado en el que termina la transicion
	 * @return estado final
	 */
	public EstadoMealy getEstadoFinal() {
		return estadoFinal;
	}


	/**
	 * Modifica el estado en el que termina la transicion
	 * @param estadoFinal nuevo estado final
	 */
	public void setEstadoFinal(EstadoMealy estadoFinal) {
		this.estadoFinal = estadoFinal;
	}


	/**
	 * Retorna la respuesta de la transicion
	 * @return
	 */
	public Respuesta getRespuesta() {
		return respuesta;
	}

	/**
	 * Modifica la respuesta de la transicion
	 * @param respuesta
	 */

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
}
