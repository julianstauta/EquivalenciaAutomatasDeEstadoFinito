package Equivalencia.vista;

import Equivalencia.controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class VistaTablas {

	public static final String MEALY = "Mealy", MOORE = "Moore";
	
	private String tipo;
	private String[] estimulos;
	private controller cont;
	
	@FXML
	private TableView<String[]> table1;
	@FXML
	private TableView<String[]> table2;
	@FXML
	private Button butAgr1;
	@FXML
	private Button butAgr2;
	@FXML
	private Button butDel1;
	@FXML
	private Button butDel2;
	@FXML
	private Button butEq;
	@FXML
	private MenuItem nuevaEquivalencia; 
	@FXML
	private MenuItem salir;
	@FXML
	private MenuItem info;
	@FXML
	private Label resultado;
	
	public VistaTablas() {
	}
	
	
	public VistaTablas(String tipo, String[] estimulos, controller c) {
		this.tipo = tipo;
		this.estimulos = estimulos;
		cont = c;		
	}
	
	@FXML
	public void initialize() {
		table1.getColumns().clear();
		table2.getColumns().clear();
		
		//Creacion columnas
		TableColumn colum = new TableColumn<>("Estados");
		TableColumn colum2 = new TableColumn<>("Estados");
		colum.setSortable(false);
		colum2.setSortable(false);
		table1.getColumns().add(colum);
		table2.getColumns().add(colum2);
		for (int i = 0; i < estimulos.length; i++) {
			colum = new TableColumn<>(estimulos[i]);
			colum2 = new TableColumn<>(estimulos[i]);
			colum.setSortable(false);
			colum2.setSortable(false);
			colum2.setCellFactory(TextFieldTableCell.forTableColumn());
			table1.getColumns().add(colum);
			table2.getColumns().add(colum2);
		}
		if (tipo.equals(MOORE)) {
			colum = new TableColumn<>("Salida");
			colum2 = new TableColumn<>("Salida");
			colum.setSortable(false);
			colum2.setSortable(false);
			colum.setCellFactory(TextFieldTableCell.forTableColumn());
			colum2.setCellFactory(TextFieldTableCell.forTableColumn());
			table1.getColumns().add(colum);
			table2.getColumns().add(colum2);
		}
		for (int i = 0; i < table1.getColumns().size(); i++) {
			TableColumn tc = table1.getColumns().get(i);
			TableColumn tc2 = table2.getColumns().get(i);
			final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setCellFactory(TextFieldTableCell.forTableColumn());
            tc.setOnEditCommit( new EventHandler<CellEditEvent>() {
		        @Override
		        public void handle(CellEditEvent t) {
		        	table1.getItems().get(t.getTablePosition().getRow())[t.getTablePosition().getColumn()] = ""+t.getNewValue();
		        }
		    });
            tc2.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc2.setCellFactory(TextFieldTableCell.forTableColumn());
            tc2.setOnEditCommit( new EventHandler<CellEditEvent>() {
		        @Override
		        public void handle(CellEditEvent t) {
		        	table1.getItems().get(t.getTablePosition().getRow())[t.getTablePosition().getColumn()] = ""+t.getNewValue();
		        }
		    });
		}
		
		butAgr1.setOnAction(e -> AgregarEstado1());
		butAgr2.setOnAction(e -> AgregarEstado2());
		
		butDel1.setOnAction(e -> eliminar(table1));
		butDel2.setOnAction(e -> eliminar(table2));
		
		salir.setOnAction(e -> System.exit(0));
		info.setOnAction(e -> {
			Alert a = new Alert(AlertType.INFORMATION, "Aplicacion creada con el proposito de determinar\n"
					+ "la equivalencia entre des automatas.\n\n"
					+ "Deasrrollada por: Luis Miguel Paz Velazques\n"
					+ "\t\t\t  y Julian Santiago Tauta Chaparro");
			a.show();
		});
		nuevaEquivalencia.setOnAction(e -> cont.showSeleccionAutomata());
		
	}
	
	public void AgregarEstado1() {
		String[] row = new String[estimulos.length+1];
		for (int i = 0; i < row.length; i++) {
			row[i] = "";			
		}
		table1.getItems().add(row);
	}

	public void AgregarEstado2() {
		String[] row = new String[estimulos.length+1];
		for (int i = 0; i < row.length; i++) {
			row[i] = "";
		}
		table2.getItems().add(row);
	}

	public void eliminar(TableView table) {
		ObservableList all, selected;
		selected = table.getSelectionModel().getSelectedItems();
		all = table.getItems();
		selected.forEach(all :: remove);
	}
	
	public void mostrarResultado( String resultado ) {
		this.resultado.setText(resultado);
	}
	
}

