package modelo;

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
					automataMoore1=new AutomataMoore(table);
					break;
				case 2:
					automataMoore2=new AutomataMoore(table);
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
	
	
	

}
