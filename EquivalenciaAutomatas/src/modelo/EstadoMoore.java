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
	/**
	 * Numero de maquina a la que pertenece el estado
	 */
	
	private int maquina;
	
	/**
	 * Constructor de la clase
	 * @param id identificador del estado
	 * @param numero numero de la maquina a la que pertenece el estado
	 */
	
	public EstadoMoore(String id, int numero) {
		this.id = id;
		maquina=numero;
		transiciones=new HashMap();
	}
	
	/**
	 * Retorna el identificador del estado
	 * @return identificador del estado
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Modifica el identificador del estado
	 * @param id nuevo identificador
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * retorna las transiciones del estado
	 * @return
	 */
	public HashMap<Simbolo, EstadoMoore> getTransiciones() {
		return transiciones;
	}
	
	/**
	 * Modifica las transiciones del estado
	 * @param transiciones nuevas transiciones
	 */
	public void setTransiciones(HashMap<Simbolo, EstadoMoore> transiciones) {
		this.transiciones = transiciones;
	}
	
	/**
	 * retorna la respuesta en el estado
	 * @return respuesta del estado
	 */
	public Respuesta getRespuesta() {
		return respuesta;
	}
	/**
	 * Modifica la respuesta del estado
	 * @param respuesta nueva respuesta
	 */
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}
	/**
	 * retorna el numero de maquina a la que pertenece el estado
	 * @return numero de maquina
	 */
	public int getMaquina() {
		return maquina;
	}
	
	/**
	 * Modiofica el numero de la maquina a la que pertenece el automata
	 * @param numMaquina nuevo numero de maquina
	 */
	public void setMaquina(int maquina) {
		this.maquina = maquina;
	}
	
	
	
	
	
	
	
	
	
	
	

}
