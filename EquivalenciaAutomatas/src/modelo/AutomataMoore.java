package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class AutomataMoore {

	/**
	 * Estado inicial del automata
	 */
	
	private EstadoMoore estadoInicial;
	/**
	 * Estados del automata
	 */
	private HashMap<String, EstadoMoore> estados;
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
	public AutomataMoore(String tabla, int numero, ArrayList<Simbolo> simbolos) {
		String[] lineas=tabla.split("\n");
		StringTokenizer skt=new StringTokenizer(lineas[0].trim());
		if(simbolos==null) {
			simbolos=new ArrayList<>();
			for (int i = 0; i < lineas.length; i++) {
				String actualSimbol=skt.nextToken();
				Simbolo nuevo=new Simbolo(actualSimbol);
				simbolos.add(nuevo);
			}
			
		}
		
		estados=new HashMap();
		respuestas=new HashMap();
		for (int i = 0; i < lineas.length; i++) {
			String [] actualLine=lineas[i].split(" ");
			String estado=actualLine[0];
			String respuesta=actualLine[actualLine.length-1];
			EstadoMoore actual=estados.get(estado);
			if(actual==null) {
				actual=new EstadoMoore(estado, numero);
				estados.put(estado, actual);
			}
			Respuesta respuestaActual=respuestas.get(respuesta);
			if(respuestaActual==null) {
				respuestaActual=new Respuesta(respuesta);
				respuestas.put(respuesta, respuestaActual);
			}
			
			actual.setRespuesta(respuestaActual);
			if(estadoInicial==null) {
				estadoInicial=actual;
			}
			for (int j = 1; j < actualLine.length-1; j++) {
				String estadoTransicion=actualLine[j];
				EstadoMoore estadoMooreTransicion=estados.get(estadoTransicion);
				if(estadoMooreTransicion==null) {
					estadoMooreTransicion=new EstadoMoore(estadoTransicion, numero);
					estados.put(estadoTransicion, estadoMooreTransicion);
				}
				actual.getTransiciones().put(simbolos.get(j-1), estadoMooreTransicion);
			}
			
		}
		
	}

	/**
	 * Metodo que retorna el estado inicial del automata
	 * 
	 * @return estado inicial
	 */
	public EstadoMoore getEstadoInicial() {
		return estadoInicial;
	}

	
	/**
	 * Metodo para modificar el estado inicial del automata
	 * @param estadoInicial
	 */
	public void setEstadoInicial(EstadoMoore estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	/**
	 * retosna los estados del automata
	 * @return
	 */
	public HashMap<String, EstadoMoore> getEstados() {
		return estados;
	}

	/**
	 * Modifica los estados del automata
	 * @param estados estados nuevos
	 */
	
	public void setEstados(HashMap<String, EstadoMoore> estados) {
		this.estados = estados;
	}

	/**
	 * Retorna las respuestas del automata a los estimulos
	 * @return
	 */
	public HashMap<String, Respuesta> getRespuestas() {
		return respuestas;
	}
	/**
	 * Modifica las respuestas del automata a los estimulos
	 * @return
	 */
	
	public void setRespuestas(HashMap<String, Respuesta> respuestas) {
		this.respuestas = respuestas;
	}
	
	
	
	
}
