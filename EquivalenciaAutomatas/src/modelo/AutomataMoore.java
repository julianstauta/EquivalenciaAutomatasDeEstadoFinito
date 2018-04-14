package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class AutomataMoore {

	
	private EstadoMoore estadoInicial;
	
	private HashMap<String, EstadoMoore> estados;
	
	private ArrayList<Simbolo> simbolos;
	
	
	private HashMap<String, Respuesta> respuestas;
	
	public AutomataMoore(String tabla) {
		String[] lineas=tabla.split("\n");
		StringTokenizer skt=new StringTokenizer(lineas[0].trim());
		simbolos=new ArrayList();
		ArrayList<String> simbols=new ArrayList();
		for (int i = 0; i < lineas.length; i++) {
			String actualSimbol=skt.nextToken();
			Simbolo nuevo=new Simbolo(actualSimbol);
			simbolos.add(nuevo);
		}
		estados=new HashMap();
		respuestas=new HashMap();
		for (int i = 1; i < lineas.length; i++) {
			String [] actualLine=lineas[i].split(" ");
			String estado=actualLine[0];
			String respuesta=actualLine[actualLine.length-1];
			EstadoMoore actual=estados.get(estado);
			if(actual==null) {
				actual=new EstadoMoore(estado);
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
					estadoMooreTransicion=new EstadoMoore(estadoTransicion);
					estados.put(estadoTransicion, estadoMooreTransicion);
				}
				actual.getTransiciones().put(simbolos.get(j-1), estadoMooreTransicion);
			}
			
		}
		
	}
	
	
}
