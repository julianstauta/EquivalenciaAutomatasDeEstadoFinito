package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class EstadoMoore {
	/**
	 * Nombre del estado(Identificador)
	 */
	private String id;
	/**
	 * Trancisiones desde este estado
	 */
	private HashMap<Simbolo, EstadoMoore> transiciones;
	/**
	 * Respuesta en el estado
	 */
	private Respuesta respuesta;
	
	
	
	
	
	public EstadoMoore(String id) {
		this.id = id;
		transiciones=new HashMap();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HashMap<Simbolo, EstadoMoore> getTransiciones() {
		return transiciones;
	}
	public void setTransiciones(HashMap<Simbolo, EstadoMoore> transiciones) {
		this.transiciones = transiciones;
	}
	public Respuesta getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
	
	
	
	
	
	
	
	

}
