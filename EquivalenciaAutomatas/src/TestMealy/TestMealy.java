package TestMealy;

import java.util.ArrayList;

import modelo.*;

public class TestMealy {

	public static void main(String[] args) {
		Programa prog = new Programa();
		ArrayList<Simbolo> simbolos = new ArrayList<>();
		simbolos.add(new Simbolo("a"));
		simbolos.add(new Simbolo("b"));
		prog.setSimbolos(simbolos);
		String automata1 = "A B,1 C,0\n"
						+ "B C,0 A,0\n"
						+ "C A,1 A,1";
		String automata2 = "D E,1 F,0\n"
						+ "E F,0 D,0\n"
						+ "F D,1 D,1";
		prog.agregarAutomata(false, 1, automata1);
		prog.agregarAutomata(false, 2, automata2);
		
		System.out.println(prog.equivalenciaAutomatas());
		
	}
	
}
