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
	
	public EstadoMealy(String id, int numMaquina) {
		this.id=id;
		this.numMaquina=numMaquina;
		transiciones=new HashMap<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HashMap<Simbolo, TransicionMealy> getTransiciones() {
		return transiciones;
	}

	public void setTransiciones(HashMap<Simbolo, TransicionMealy> transiciones) {
		this.transiciones = transiciones;
	}

	public int getNumMaquina() {
		return numMaquina;
	}

	public void setNumMaquina(int numMaquina) {
		this.numMaquina = numMaquina;
	}
	
	
	
	

}
