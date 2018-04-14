package modelo;

public class TransicionMealy {

	
	/**
	 * Estado inicial de la transicion
	 */
	private EstadoMoore estadoInicial;
	/**
	 * Estado final de la transicion
	 */
	private EstadoMoore estadoFinal;
	/**
	 * Simbolo que genera la transicion
	 */
	private String respuesta;
	
	
	/**
	 * Constructor de la clase trancision
	 * @param estadoInicial
	 * @param estadoFinal
	 * @param simbolo
	 */
	public TransicionMealy(EstadoMoore estadoInicial, EstadoMoore estadoFinal, String respuesta) {
		this.estadoInicial = estadoInicial;
		this.estadoFinal = estadoFinal;
		this.respuesta=respuesta;
	}
	
	public EstadoMoore getEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(EstadoMoore estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	public EstadoMoore getEstadoFinal() {
		return estadoFinal;
	}
	public void setEstadoFinal(EstadoMoore estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
}
