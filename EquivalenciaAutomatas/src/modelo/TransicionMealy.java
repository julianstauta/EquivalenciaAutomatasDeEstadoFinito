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


	public EstadoMealy getEstadoFinal() {
		return estadoFinal;
	}


	public void setEstadoFinal(EstadoMealy estadoFinal) {
		this.estadoFinal = estadoFinal;
	}


	public Respuesta getRespuesta() {
		return respuesta;
	}


	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
}
