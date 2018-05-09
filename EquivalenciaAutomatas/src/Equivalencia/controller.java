package Equivalencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Equivalencia.vista.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Programa;
import modelo.Simbolo;

/**
 * Clase controladora del programa
 * @author JulianSantiago and LuisMiguel
 *
 */
public class controller extends Application {

	/**
	 * Ventana primaria del programa
	 */
	private Stage primaryStage;
	
	/**
	 * Layout principal del programa
	 */
	private BorderPane mainlayout;
	
	/**
	 * Clase pricipal que contiene la parte logica del programa
	 */
	private Programa programa;
	
	/**
	 * Controlador de la ventana para visualizar los automatas
	 */
	private VistaTablas tablas;
	
	/**
	 * Valor booleano que representa si el tipo de automata a tratar es moore
	 */
	private boolean moore;
	
	/**
	 * Ejecuta las funcionalidades graficas de la aplicacion
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Equivalencia Automatas");
		showSeleccionAutomata();
	}
	
	/**
	 * Muestra la ventana de seleccion de automata
	 */
	public void showSeleccionAutomata(){
		programa = new Programa();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(controller.class.getResource("vista/SeleccionAutomata.fxml"));
			loader.setController(new VistaSeleccion(this));
			mainlayout = loader.load();
			Scene scene = new Scene(mainlayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra la ventana de visualizacion de automatas
	 * @param tipo String. Tipo de automatas Mealy o Moore
	 * @param estimulos. String[]. Estimulos que entran a los estados de los automatas.
	 */
	public void showTablas(String tipo, String[] estimulos) {
		
		try {
			if (!tipo.equals(VistaTablas.MEALY)&&!tipo.equals(VistaTablas.MOORE)) {
				throw new NoSelecionoTipoException();
			}
			if (estimulos.length == 0) {
				throw new ErrorEnFormatoEstimulos();
			}
			for (int i = 0; i < estimulos.length; i++) {
				if (estimulos[i].equals("") || estimulos[i].equals(" ") || estimulos[i] == null) {
					throw new ErrorEnFormatoEstimulos();
				}
			}
			//Guarda Simbolos
			ArrayList<Simbolo> simbolos = new ArrayList<>();
			for (int i = 0; i < estimulos.length; i++) {
				Simbolo s = new Simbolo(estimulos[i]);
				simbolos.add(s);
			}
			programa.setSimbolos(simbolos);
			if (tipo.equals(VistaTablas.MOORE)) {
				moore = true;
			} else {
				moore = false;
			}
			//Abre ventana Tablas
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(controller.class.getResource("vista/Tablas.fxml"));
			tablas = new VistaTablas(tipo, estimulos, this);
			loader.setController(tablas);
			mainlayout = loader.load();
			Scene scene = new Scene(mainlayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSelecionoTipoException e2) {
			Alert a = new Alert(AlertType.ERROR, e2.getMessage());
			a.show();
		} catch (ErrorEnFormatoEstimulos e3) {
			Alert a = new Alert(AlertType.ERROR, e3.getMessage());
			a.show();
		}
	}
	
	/**
	 * Determina la equivalencia entre dos automatas en forma de string. los automatas tienen el siguiente formato:<br>
	 * <b>Si es Mealy</b>:<br>
	 * Estado1(espacio)EstadoTransicion1(espacio)EstadoTransicion2(espacio)...(espacio)EstadoTransicionN(Salto de Linea)<br>
	 * Estado2(espacio)EstadoTransicion1(espacio)EstadoTransicion2(espacio)...(espacio)EstadoTransicionN(Salto de Linea)<br>
	 * ...(Salto de linea)<br>
	 * EstadoK(espacio)EstadoTransicion1(espacio)EstadoTransicion2(espacio)...(espacio)EstadoTransicionN(Salto de Linea)<br>
	 * con N y K >= 1<br>
	 * <b>Si es Moore</b>:<br>
	 * Estado1(espacio)EstadoTransicion1(espacio)EstadoTransicion2(espacio)...(espacio)EstadoTransicionN(espacio)Salida(Salto de Linea)<br>
	 * Estado2(espacio)EstadoTransicion1(espacio)EstadoTransicion2(espacio)...(espacio)EstadoTransicionN(espacio)Salida(Salto de Linea)<br>
	 * ...(Salto de linea)<br>
	 * EstadoK(espacio)EstadoTransicion1(espacio)EstadoTransicion2(espacio)...(espacio)EstadoTransicionN(espacio)Salida(Salto de Linea)<br>
	 * con N y K >= 1<br>
	 * @param automata1 String. Automata numero 1 con el formato especificado.
	 * @param automata2 String. Automata numero 2 con el formato especificado.
	 */
	public void determinarEquivalencia(String automata1, String automata2) {
		try {
			validarAutomata(automata1);
			validarAutomata(automata2);
			programa.agregarAutomata(moore, 1, automata1);
			programa.agregarAutomata(moore, 2, automata2);
			String resultado = programa.equivalenciaAutomatas();
			tablas.mostrarResultado(resultado);
		} catch (Exception e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR, e.getMessage());
			a.show();
		}
		
	}
	
	/**
	 * Valida que el automata ingresado por parametro sea un automata valido.
	 * En un automata valido no se pueden repetir estados, no pueden haber estados con identificador vacio, los estados hacia donde van las<br>
	 * tranciciones deben existir en el automata, las salidas solo pueden ser 1 o 0, y si el automata es de moore las tranciciones deben ser<br>
	 * de la forma "EstadoTransicion,Salida"<br>
	 * @param automata String. Automata ingresado por paramentro.
	 * @throws Exception En caso que el automata no sea valido.
	 */
	public void validarAutomata(String automata) throws Exception{
		if (automata.equals("")) {
			throw new Exception("Debe definir los 2 automatas");
		}
		HashMap<String, String> estados = new HashMap<>();
		String lineas[] = automata.split("\n");
		for (int i = 0; i < lineas.length; i++) {
			String[] linea = lineas[i].split(" ");
			if (linea[0].equals("")||linea[0].equals(" ")||linea[0] == null) {
				throw new Exception("Nombre erroneo de estados, no pueden ser vacios");
			}
			if (!estados.containsKey(linea[0])) {
				estados.put(linea[0], linea[0]);
			} else {
				throw new Exception("No se puede repetir estados");
			}
		}
		if (moore) {
			for (int i = 0; i < lineas.length; i++) {
				String[] linea = lineas[i].split(" ");
				for (int j = 1; j < linea.length; j++) {
					if (j != linea.length-1 &&!estados.containsKey(linea[j])) {
						throw new Exception("Existen trasiciones hacia estados inexistentes");
					}
					if (j == linea.length-1 && (!linea[j].equals("0") && !linea[j].equals("1"))) {
						throw new Exception("Solo hay 2 salidas validas 0 o 1");
					}
				}	
			}
		} else {
			for (int i = 0; i < lineas.length; i++) {
				String[] linea = lineas[i].split(" ");
				for (int j = 1; j < linea.length; j++) {
					String[] transicion = linea[j].split(",");
					if (transicion.length!=2) {
						throw new Exception("Las transicion esta mal definidas");
					}
					if (!estados.containsKey(transicion[0])) {
						throw new Exception("Existen trasiciones hacia estados inexistentes");
					}
					if (!transicion[1].equals("0")&&!transicion[1].equals("1")) {
						throw new Exception("Solo hay 2 salidas validas en la transicion 0 o 1");
					}
				}	
			}
		}
	}
	
	/**
	 * Inicia la ejecucion del programa.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
