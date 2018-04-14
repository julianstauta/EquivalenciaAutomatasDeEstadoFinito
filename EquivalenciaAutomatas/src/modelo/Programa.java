package modelo;

import java.io.LineNumberInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Programa {
	
	private AutomataMoore automataMoore1;
	
	private AutomataMoore automataMoore2;
	
	private AutomataMealy automataMealy1;
	
	private AutomataMealy automataMealy2;
	
	
	
	public Programa() {
		
	}
	
	
	
	public boolean agregarAutomata(boolean moore,int automata,String table) {
		try {
			if(moore) {
				switch (automata) {
				case 1:
					automataMoore1=new AutomataMoore(table,1);
					break;
				case 2:
					automataMoore2=new AutomataMoore(table,2);
					break;

				default:
					return false;
				}
			}else {
				switch (automata) {
				case 1:
					automataMealy1=new AutomataMealy(table);
					break;
				case 2:
					automataMealy2=new AutomataMealy(table);
					break;

				default:
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public String equivalenciaAutomatas() {
		String resultado="";
		

		return resultado;
	}
	
	public void eliminarEstadosInalcanzables(AutomataMoore moore) {
		
		HashSet<EstadoMoore> estadosAlcanzables=new HashSet<>();
		
		EstadoMoore actual=moore.getEstadoInicial();
		
		int total=moore.getEstados().size();
		
		LinkedList<EstadoMoore> cola=new LinkedList<>();
		cola.add(actual);
		while(!cola.isEmpty()&&estadosAlcanzables.size()<total) {
			actual=cola.poll();
			if(!estadosAlcanzables.contains(actual)) {
				estadosAlcanzables.add(actual);
				
				for (Iterator<EstadoMoore> iterator=actual.getTransiciones().values().iterator() ; iterator.hasNext();) {
					EstadoMoore estadoMoore = (EstadoMoore) iterator.next();
					cola.add(estadoMoore);
					
				}
			}
			
		}
		
		
		for (Iterator<EstadoMoore> iterator=moore.getEstados().values().iterator(); iterator.hasNext();) {
			EstadoMoore estadoMoore = (EstadoMoore) iterator.next();
			if(!estadosAlcanzables.contains(estadoMoore)) {
				moore.getEstados().remove(estadoMoore.getId());
			}
		}
		
	}
	
	
	
	public void renombrar(HashSet<EstadoMoore> estadosMaquina1,HashSet<EstadoMoore> estadosMaquina2) {
		HashSet<String> nombres=new HashSet<>();
		
		
		
		for (Iterator iterator = estadosMaquina1.iterator(); iterator.hasNext();) {
			EstadoMoore string = (EstadoMoore) iterator.next();
			nombres.add(string.getId());
		}
		
		char newName='A';
		
		for (Iterator iterator = estadosMaquina2.iterator(); iterator.hasNext();) {
			EstadoMoore string = (EstadoMoore) iterator.next();
			if(nombres.contains(string.getId())) {
				while(nombres.contains(newName+"")) {
					newName++;
				}
				string.setId(newName+"");
				nombres.add(newName+"");
			}
		}
	}
	
	
	public boolean equivalentes(HashSet<EstadoMoore> estadosMaquina1,HashSet<EstadoMoore> estadosMaquina2) {
		boolean equivalentes=true;
		
		
		
		return equivalentes;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
