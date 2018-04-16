package modelo;

import java.util.HashMap;

public class EstadoMealy {
	/**
	 * Nombre del estado(Identificador)
	 */
	private String id;
	/**
	 * Trancisiones desde este estado
	 */
	private HashMap<Simbolo, TransicionMealy> transiciones;
	
	
	/**
	 * Numero de maquina a la que pertenece
	 */
	
	private int numMaquina;
	/**
	 * Constructor de la clase
	 * @param id identificador del estado
	 * @param numMaquina numero de maquina a la que pertenece
	 */
	public EstadoMealy(String id, int numMaquina) {
		this.id=id;
		this.numMaquina=numMaquina;
		transiciones=new HashMap<>();
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
	public HashMap<Simbolo, TransicionMealy> getTransiciones() {
		return transiciones;
	}

	/**
	 * Modifica las transiciones del estado
	 * @param transiciones nuevas transiciones
	 */
	public void setTransiciones(HashMap<Simbolo, TransicionMealy> transiciones) {
		this.transiciones = transiciones;
	}

	/**
	 * retorna el numero de maquina a la que pertenece el estado
	 * @return numero de maquina
	 */
	public int getNumMaquina() {
		return numMaquina;
	}

	/**
	 * Modiofica el numero de la maquina a la que pertenece el automata
	 * @param numMaquina nuevo numero de maquina
	 */
	public void setNumMaquina(int numMaquina) {
		this.numMaquina = numMaquina;
	}
	
	
	
	

}
