package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class AutomataMealy {
	
	/**
	 * Estado inicial del automata
	 */

	private EstadoMealy estadoInicial;
	
	/**
	 * Estados del automata
	 */
	private HashMap<String, EstadoMealy> estados;
	/**
	 * Respuestas a estimulos del automata
	 */
	private HashMap<String, Respuesta> respuestas;
	
	/**
	 * Constructor del automata
	 * 
	 * @param table tabla que describe el automata
	 * @param numero numero que identifica que automata es
	 * @param simbolos simbolos o estimulos del automata
	 */
	public AutomataMealy(String table, int numero, ArrayList<Simbolo> simbolos) {
		String[] lineas=table.split("\n");
		
		estados=new HashMap();
		respuestas=new HashMap<>();
		for (int i = 0; i < lineas.length; i++) {
			String [] actualLine=lineas[i].split(" ");
			String estado=actualLine[0];
			EstadoMealy actual=estados.get(estado);
			if(actual==null) {
				actual=new EstadoMealy(estado, numero);
				estados.put(estado, actual);
			}
			
			if(estadoInicial==null) {
				estadoInicial=actual;
			}
			for (int j = 1; j < actualLine.length; j++) {
				String[] estadoTransicion=actualLine[j].split(",");
				EstadoMealy estadoMooreTransicion=estados.get(estadoTransicion[0]);
				if(estadoMooreTransicion==null) {
					estadoMooreTransicion=new EstadoMealy(estadoTransicion[0], numero);
					estados.put(estadoTransicion[0], estadoMooreTransicion);
				}
				
				Respuesta respuesta=respuestas.get(estadoTransicion[1]);
				if(respuesta==null) {
					respuesta=new Respuesta(estadoTransicion[1]);
					respuestas.put(estadoTransicion[1], respuesta);
				}
				if (actual.getTransiciones() == null) {
					actual.setTransiciones(new HashMap<>());
				}
				actual.getTransiciones().put(simbolos.get(j-1), new TransicionMealy(estadoMooreTransicion, respuesta));
			}
			
		}

	}

	/**
	 * Metodo que retorna el estado inicial del automata
	 * 
	 * @return estado inicial
	 */

	public EstadoMealy getEstadoInicial() {
		return estadoInicial;
	}


	/**
	 * Metodo para modificar el estado inicial del automata
	 * @param estadoInicial
	 */
	public void setEstadoInicial(EstadoMealy estadoInicial) {
		this.estadoInicial = estadoInicial;
	}


	/**
	 * retosna los estados del automata
	 * @return
	 */
	public HashMap<String, EstadoMealy> getEstados() {
		return estados;
	}

	/**
	 * Modifica los estados del automata
	 * @param estados estados nuevos
	 */

	public void setEstados(HashMap<String, EstadoMealy> estados) {
		this.estados = estados;
	}


	
	
	
	
	
}
