package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Programa {
	
	
	private ArrayList<Simbolo> simbolos;
	
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
					automataMoore1=new AutomataMoore(table,1,simbolos);
					break;
				case 2:
					automataMoore2=new AutomataMoore(table,2,simbolos);
					break;

				default:
					return false;
				}
			}else {
				switch (automata) {
				case 1:
					automataMealy1=new AutomataMealy(table,1,simbolos);
					break;
				case 2:
					automataMealy2=new AutomataMealy(table,2,simbolos);
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
		if(automataMoore1!=null&&automataMoore2!=null) {
			eliminarEstadosInalcanzables(automataMoore1);
			eliminarEstadosInalcanzables(automataMoore2);
			renombrarMoore(automataMoore1.getEstados().values(), automataMoore2.getEstados().values());
			if(equivalentesMoore(automataMoore1.getEstados().values(), automataMoore2.getEstados().values(), simbolos)) {
				resultado="Los automatas son equivalentes";
			}else {
				resultado="Los automatas no son equivalentes";
			}
		
		}else if(automataMealy1!=null&&automataMealy2!=null) {
			eliminarEstadosInalcanzables(automataMealy1);
			eliminarEstadosInalcanzables(automataMealy2);
			renombrarMealy(automataMealy1.getEstados().values(), automataMealy2.getEstados().values());
			if(equivalentesMealy(automataMealy1.getEstados().values(), automataMealy2.getEstados().values(), simbolos)) {
				resultado="Los automatas son equivalentes";
			}else {
				resultado="Los automatas no son equivalentes";
			}
		}else {
			resultado="Falta crear alguno de los automatas";
		}

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
	
	
	public void eliminarEstadosInalcanzables(AutomataMealy mealy) {
		
		HashSet<EstadoMealy> estadosAlcanzables=new HashSet<>();
		
		EstadoMealy actual=mealy.getEstadoInicial();
		
		int total=mealy.getEstados().size();
		
		LinkedList<EstadoMealy> cola=new LinkedList<>();
		cola.add(actual);
		while(!cola.isEmpty()&&estadosAlcanzables.size()<total) {
			actual=cola.poll();
			if(!estadosAlcanzables.contains(actual)) {
				estadosAlcanzables.add(actual);
				
				for (Iterator<TransicionMealy> iterator=actual.getTransiciones().values().iterator() ; iterator.hasNext();) {
					TransicionMealy transicionMealy = (TransicionMealy) iterator.next();
					cola.add(transicionMealy.getEstadoFinal());
					
				}
			}
			
		}
		
		
		for (Iterator<EstadoMealy> iterator=mealy.getEstados().values().iterator(); iterator.hasNext();) {
			EstadoMealy estadoMealy = (EstadoMealy) iterator.next();
			if(!estadosAlcanzables.contains(estadoMealy)) {
				mealy.getEstados().remove(estadoMealy.getId());
			}
		}
		
	}
	
	
	public void renombrarMoore(Collection<EstadoMoore> estadosMaquina1,Collection<EstadoMoore> estadosMaquina2) {
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
	
	public void renombrarMealy(Collection<EstadoMealy> estadosMaquina1,Collection<EstadoMealy> estadosMaquina2) {
		HashSet<String> nombres=new HashSet<>();
		
		
		
		for (Iterator iterator = estadosMaquina1.iterator(); iterator.hasNext();) {
			EstadoMealy string = (EstadoMealy) iterator.next();
			nombres.add(string.getId());
		}
		
		char newName='A';
		
		for (Iterator iterator = estadosMaquina2.iterator(); iterator.hasNext();) {
			EstadoMealy string = (EstadoMealy) iterator.next();
			if(nombres.contains(string.getId())) {
				while(nombres.contains(newName+"")) {
					newName++;
				}
				string.setId(newName+"");
				nombres.add(newName+"");
			}
		}
	}
	
	
	public boolean equivalentesMoore(Collection<EstadoMoore> estadosMaquina1,Collection<EstadoMoore> estadosMaquina2,ArrayList<Simbolo> simbolos) {
		boolean equivalentes=true;
		ArrayList<EstadoMoore> estados=new ArrayList<>();
		HashMap<EstadoMoore, Integer> pos=new HashMap<>();
		int index=0;
		for (Iterator iterator = estadosMaquina1.iterator(); iterator.hasNext();) {
			EstadoMoore estadoMoore = (EstadoMoore) iterator.next();
			estados.add(estadoMoore);
			pos.put(estadoMoore, index);
			index++;
		}
		
		for (Iterator iterator = estadosMaquina2.iterator(); iterator.hasNext();) {
			EstadoMoore estadoMoore = (EstadoMoore) iterator.next();
			estados.add(estadoMoore);
			pos.put(estadoMoore, index);
			index++;
			
		}
		int total=estadosMaquina1.size()+estadosMaquina2.size();
		UnionFind uf=new UnionFind(total);
		for (int i = 0; i < estados.size(); i++) {
			for (int j = i+1; j < estados.size(); j++) {
				if(estados.get(i).getRespuesta().getId().equals(estados.get(j).getRespuesta().getId())) {
					uf.unionSet(i, j);
				}
			}
		}
		
		boolean salir=false;
		
		while(!salir) {
			UnionFind control=new UnionFind(total);
			for (int i = 0; i < estados.size(); i++) {
				for (int j = i+1; j < estados.size(); j++) {
					if(uf.isSameSet(i, j)) {
						EstadoMoore uno=estados.get(i);
						EstadoMoore dos=estados.get(j);
						boolean same=true;
						for (int k = 0; k < simbolos.size()&&same; k++) {
							if(!uf.isSameSet(pos.get(uno.getTransiciones().get(simbolos.get(k))), pos.get(dos.getTransiciones().get(simbolos.get(k))))) {
								same=false;
							}
						}
						if(same) {
							control.unionSet(i, j);
						}
						
					}
				}
			}
			if(uf.numDisjointSets()==control.numDisjointSets()) {
				salir=true;
			}
			uf=control;
		}
		HashSet<String> conteo=new HashSet<>();
		for (int i = 0; i < estados.size(); i++) {
			String actual=uf.findSet(i)+"-"+estados.get(i).getMaquina();
			conteo.add(actual);
		}
		
		if(conteo.size()!=uf.numDisjointSets()*2) {
			equivalentes=false;
		}
		return equivalentes;
	}
	
	public boolean equivalentesMealy(Collection<EstadoMealy> estadosMaquina1,Collection<EstadoMealy> estadosMaquina2,ArrayList<Simbolo> simbolos) {
		boolean equivalentes=true;
		ArrayList<EstadoMealy> estados=new ArrayList<>();
		HashMap<EstadoMealy, Integer> pos=new HashMap<>();
		int index=0;
		for (Iterator iterator = estadosMaquina1.iterator(); iterator.hasNext();) {
			EstadoMealy estadoMoore = (EstadoMealy) iterator.next();
			estados.add(estadoMoore);
			pos.put(estadoMoore, index);
			index++;
		}
		
		for (Iterator iterator = estadosMaquina2.iterator(); iterator.hasNext();) {
			EstadoMealy estadoMoore = (EstadoMealy) iterator.next();
			estados.add(estadoMoore);
			pos.put(estadoMoore, index);
			index++;
			
		}
		int total=estadosMaquina1.size()+estadosMaquina2.size();
		UnionFind uf=new UnionFind(total);
		for (int i = 0; i < estados.size(); i++) {
			for (int j = i+1; j < estados.size(); j++) {
				EstadoMealy uno=estados.get(i);
				EstadoMealy dos=estados.get(j);
				boolean salir=false;
				for (int k = 0; k < simbolos.size()&&!salir; k++) {
					if(!uno.getTransiciones().get(simbolos.get(k)).getRespuesta().getId().equals(dos.getTransiciones().get(simbolos.get(k)).getRespuesta().getId())) {
						salir=true;
					}
				}
				
				
				if(!salir) {
					uf.unionSet(i, j);
				}
			}
		}
		
		boolean salir=false;
		
		while(!salir) {
			UnionFind control=new UnionFind(total);
			for (int i = 0; i < estados.size(); i++) {
				for (int j = i+1; j < estados.size(); j++) {
					if(uf.isSameSet(i, j)) {
						EstadoMealy uno=estados.get(i);
						EstadoMealy dos=estados.get(j);
						boolean same=true;
						for (int k = 0; k < simbolos.size()&&same; k++) {
							if(!uf.isSameSet(pos.get(uno.getTransiciones().get(simbolos.get(k)).getEstadoFinal()), pos.get(dos.getTransiciones().get(simbolos.get(k)).getEstadoFinal()))) {
								same=false;
							}
						}
						if(same) {
							control.unionSet(i, j);
						}
						
					}
				}
			}
			if(uf.numDisjointSets()==control.numDisjointSets()) {
				salir=true;
			}
			uf=control;
		}
		HashSet<String> conteo=new HashSet<>();
		for (int i = 0; i < estados.size(); i++) {
			String actual=uf.findSet(i)+"-"+estados.get(i).getNumMaquina();
			conteo.add(actual);
		}
		
		if(conteo.size()!=uf.numDisjointSets()*2) {
			equivalentes=false;
		}
		return equivalentes;
	}



	public ArrayList<Simbolo> getSimbolos() {
		return simbolos;
	}



	public void setSimbolos(ArrayList<Simbolo> simbolos) {
		this.simbolos = simbolos;
	}
	
	
	
	
	
	
	
	
	
	

}
