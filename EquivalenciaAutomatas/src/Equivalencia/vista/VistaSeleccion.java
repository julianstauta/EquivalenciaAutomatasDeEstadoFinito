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

public class VistaSeleccion {

	private controller cont;
	
	@FXML
	private ComboBox comboBox;
	@FXML
	private TextField txtEstim;
	@FXML
	private Button butIniciar;
	
	public VistaSeleccion() {
	}
	
	public VistaSeleccion(controller c) {
		cont = c;
	}
	
	@FXML
	public void initialize() {
		comboBox.getItems().addAll("Mealy", "Moore");
		butIniciar.setOnAction(e -> mostarTablas());
	}
	
	public void mostarTablas() {
		String tipo = comboBox.getValue()+"";
		String[] estimulos = txtEstim.getText().split(",");
		cont.showTablas(tipo, estimulos);		
	}
	
}
