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

/**
 * Clase que sirve para controlar la ventana de los automatas
 * @author JulianSantiago and LuisMiguel
 *
 */
public class VistaTablas {

	/**
	 * Constantes de tipos de automata<br>
	 * MEALY: Tipo mealy<br>
	 * MOORE: Tipo moore
	 */
	public static final String MEALY = "Mealy", MOORE = "Moore";
	
	/**
	 * Tipo de automata que se esta mostrando
	 */
	private String tipo;
	
	/**
	 * Estimulos que entran a los estados
	 */
	private String[] estimulos;
	
	/**
	 * Controlador de la clase
	 */
	private controller cont;
	
	/**
	 * Tabla automata 1
	 */
	@FXML
	private TableView<String[]> table1;
	
	/**
	 * Tabla automata 2
	 */
	@FXML
	private TableView<String[]> table2;
	@FXML
	
	/**
	 * Boton agregar a tabla 1
	 */
	private Button butAgr1;
	@FXML
	
	/**
	 * Boton agregar a tabla 2
	 */
	private Button butAgr2;
	
	/**
	 * Boton borrar tabla 1
	 */
	@FXML
	private Button butDel1;
	
	/**
	 * Boton borra tabla 2
	 */
	@FXML
	private Button butDel2;
	
	/**
	 * Boton calcular equivalencia
	 */
	@FXML
	private Button butEq;
	
	/**
	 * Menu item nueva equivalencia
	 */
	@FXML
	private MenuItem nuevaEquivalencia; 
	
	/**
	 * Menu item salir
	 */
	@FXML
	private MenuItem salir;
	
	/**
	 * Menu item info
	 */
	@FXML
	private MenuItem info;
	
	/**
	 * Label para mostrar el resultado
	 */
	@FXML
	private Label resultado;
	
	/**
	 * Label numero de estados automata 1
	 */
	@FXML
	private Label num1;
	
	/**
	 * Label numero de estados automata 2
	 */
	@FXML
	private Label num2;
	
	/**
	 * Constructor generico de la clase
	 */
	public VistaTablas() {
	}
	
	/**
	 * Constructor de la clase
	 * @param tipo String. El tipo de automata que se va a mostar MEALY o MOORE
	 * @param estimulos String[]. Arreglo de estimulos que entran a los estados
	 * @param c controller. Controlador de la clase
	 */
	public VistaTablas(String tipo, String[] estimulos, controller c) {
		this.tipo = tipo;
		this.estimulos = estimulos;
		cont = c;		
	}
	
	/**
	 * Se encarga de inicializar algunos apectos y funciones de la parte grafica
	 */
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
		        	table2.getItems().get(t.getTablePosition().getRow())[t.getTablePosition().getColumn()] = ""+t.getNewValue();
		        }
		    });
		}
		
		butAgr1.setOnAction(e -> AgregarEstado1());
		butAgr2.setOnAction(e -> AgregarEstado2());
		
		butDel1.setOnAction(e -> {eliminar(table1);
							num1.setText("Numero de estados: " + table1.getItems().size());
							});
		butDel2.setOnAction(e -> {eliminar(table2);
							num2.setText("Numero de estados: " + table2.getItems().size());
							});
		butEq.setOnAction(e -> calcularEquivalencia());
		
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
	
	/**
	 * Agrega una fila vacia a la tabla 1
	 */
	public void AgregarEstado1() {
		String[] row = new String[table1.getColumns().size()];
		for (int i = 0; i < row.length; i++) {
			row[i] = "";			
		}
		table1.getItems().add(row);
		num1.setText("Numero de estados: " + table1.getItems().size());
	}

	/**
	 * Agrega una fila vacia a la tabla 1
	 */
	public void AgregarEstado2() {
		String[] row = new String[table1.getColumns().size()];
		for (int i = 0; i < row.length; i++) {
			row[i] = "";
		}
		table2.getItems().add(row);
		num2.setText("Numero de estados: " + table2.getItems().size());
	}

	/**
	 * Elimina la fila seleccionada de la tabla pasada por parametro
	 * @param table TableView. Tabla de la que se va a eliminar la fila
	 */
	public void eliminar(TableView table) {
		ObservableList all, selected;
		selected = table.getSelectionModel().getSelectedItems();
		all = table.getItems();
		selected.forEach(all :: remove);
	}
	
	/**
	 * Mustra el resultado del calculo de equivalencia
	 * @param resultado
	 */
	public void mostrarResultado( String resultado ) {
		this.resultado.setText(resultado);
	}
	
	/**
	 * Transforma las tablas en strings y las envia al controlador para que este se encargue de calcular la equivalencia de los automatas
	 */
	public void calcularEquivalencia() {
		String automata1 = "";
		boolean incompleto = false;
		for (int i = 0; i < table1.getItems().size(); i++) {
			for (int j = 0; j < table1.getColumns().size(); j++) {
				if (j == table1.getColumns().size()-1) {
					automata1 += table1.getItems().get(i)[j] + "";
				} else {
					automata1 += table1.getItems().get(i)[j] + " ";
				}
				if (table1.getItems().get(i)[j].equals("")||table1.getItems().get(i)[j].equals(" ")) {
					incompleto = true;
				}
			}
			if (i != table1.getItems().size()-1) {
				automata1 += "\n";
			}
		}
		if (incompleto) {
			automata1 = "";
		}
		String automata2 = "";
		incompleto = false;
		for (int i = 0; i < table2.getItems().size(); i++) {
			for (int j = 0; j < table2.getColumns().size(); j++) {
				if (j == table2.getColumns().size()-1) {
					automata2 += table2.getItems().get(i)[j] + "";
				} else {
					automata2 += table2.getItems().get(i)[j] + " ";
				}
				if (table2.getItems().get(i)[j].equals("")||table2.getItems().get(i)[j].equals(" ")) {
					incompleto = true;
				}
			}
			if (i != table2.getItems().size()-1) {
				automata2 += "\n";
			}
		}
		if (incompleto) {
			automata2 = "";
		}
		System.out.println(automata1);
		System.out.println(automata2);
		cont.determinarEquivalencia(automata1, automata2);
	}
	
}

