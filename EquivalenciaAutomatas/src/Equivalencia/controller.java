package Equivalencia;

import java.io.IOException;

import Equivalencia.vista.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class controller extends Application {

	private Stage primaryStage;
	private BorderPane mainlayout;
	private String a;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Equivalencia Automatas");
		showSeleccionAutomata();
	}
	
	public void showSeleccionAutomata(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(controller.class.getResource("vista/SeleccionAutomata.fxml"));
			loader.setController(new VistaSeleccion(this));
			mainlayout = loader.load();
			Scene scene = new Scene(mainlayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
			//TODO
			//Guardar los simbolos = estimulos;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(controller.class.getResource("vista/Tablas.fxml"));
			loader.setController(new VistaTablas(tipo, estimulos, this));
			mainlayout = loader.load();
			Scene scene = new Scene(mainlayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSelecionoTipoException e2) {
			Alert a = new Alert(AlertType.ERROR, e2.getMessage());
			a.show();
		} catch (ErrorEnFormatoEstimulos e3) {
			Alert a = new Alert(AlertType.ERROR, e3.getMessage());
			a.show();
		}
	}
	
	public void determinarEquivalencia() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
