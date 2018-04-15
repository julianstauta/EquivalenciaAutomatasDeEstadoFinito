package Equivalencia.vista;

import java.util.StringTokenizer;

import Equivalencia.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Clase que sirve para controlar la ventana de seleccion de automatas
 * @author JulianSantiago and LuisMiguel
 *
 */
public class VistaSeleccion {

	/**
	 * Controlador de la clase
	 */
	private controller cont;
	
	/**
	 * Combobox con los tipos de automata
	 */
	@FXML
	private ComboBox comboBox;
	
	/**
	 * TextField para ingresar los estimulos
	 */
	@FXML
	private TextField txtEstim;
	
	/**
	 * Boton para iniciar con la funcionalidad principal del programa
	 */
	@FXML
	private Button butIniciar;
	
	/**
	 * Constructor generico de la clase
	 */
	public VistaSeleccion() {
	}
	
	/**
	 * Cosntructor de la clase
	 * @param c controller. Clase controladora de esta clase
	 */
	public VistaSeleccion(controller c) {
		cont = c;
	}
	
	/**
	 * Inicializa Se encarga de inicializar algunos apectos y funciones de la parte grafica
	 */
	@FXML
	public void initialize() {
		comboBox.getItems().addAll("Mealy", "Moore");
		butIniciar.setOnAction(e -> mostarTablas());
	}
	
	/**
	 * Maneja el evento generado al oprimir el boton iniciar
	 */
	public void mostarTablas() {
		String tipo = comboBox.getValue()+"";
		String[] estimulos = txtEstim.getText().split(",");
		cont.showTablas(tipo, estimulos);
	}
	
}
