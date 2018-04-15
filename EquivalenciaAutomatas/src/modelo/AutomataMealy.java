package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class AutomataMealy {

	private EstadoMealy estadoInicial;
	
	private HashMap<String, EstadoMealy> estados;
	
	private HashMap<String, Respuesta> respuestas;
	
	
	public AutomataMealy(String table, int numero, ArrayList<Simbolo> simbolos) {
		String[] lineas=table.split("\n");
		StringTokenizer skt=new StringTokenizer(lineas[0].trim());
		if(simbolos==null||simbolos.size()!=skt.countTokens()) {
			simbolos=new ArrayList<>();
			for (int i = 0; i < lineas.length; i++) {
				String actualSimbol=skt.nextToken();
				Simbolo nuevo=new Simbolo(actualSimbol);
				simbolos.add(nuevo);
			}
			
		}
		
		estados=new HashMap();
		respuestas=new HashMap();
		for (int i = 1; i < lineas.length; i++) {
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
			for (int j = 1; j < actualLine.length-1; j++) {
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
				actual.getTransiciones().put(simbolos.get(j-1), new TransicionMealy(estadoMooreTransicion, respuesta));
			}
			
		}

	}
}
