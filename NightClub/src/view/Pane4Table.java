package view;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Pane4EventEvent;
import controller.Pane4EventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Event;
import model.EventsBag;

public class Pane4Table {
	
	private TableView<Event> eventsTable;
	private ObservableList<Event> events;

	private HBox pane;

	private Pane4EventListener pane4EventListener;
	

	
	
	//------------------------------------Columns--------------------------------------------------

	public TableColumn getDateColumn() {
		TableColumn dateColumn = new TableColumn("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("date"));
		return dateColumn;
	}

	public TableColumn getEventNameColumn() {
		TableColumn eventNameColumn = new TableColumn("Event Name");
		eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
		return eventNameColumn;
	}
	

	
	//----------------------------------Table-------------------------------------------------------
	public TableView getTable() {
		events = FXCollections.observableArrayList(EventsBag.events);
		eventsTable = new TableView<Event>();

		eventsTable.setEditable(false);
		eventsTable.setMaxHeight(400);
		eventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		eventsTable.getColumns().addAll(getDateColumn(), getEventNameColumn());
		eventsTable.setItems(events);

		eventsTable.setRowFactory(e -> {
			TableRow<Event> row = new TableRow<Event>();
			row.setOnMouseClicked(ev -> {
				if (ev.getClickCount() >= 1 && (!row.isEmpty())) {
					System.out.println("Detected clicks");
					Event rowData = row.getItem();
					Pane4EventEvent ev3 = new Pane4EventEvent(this, rowData);
					if (pane4EventListener != null) {
						System.out.println("In the if statement in myTable");
						pane4EventListener.rowSelected(ev3);
					}
					 
				}
				 //paneEv = new Pane4Event();
				 //MainWindow.setCenter(paneEv.getPane());
				
			});
			return row;
		});
		return eventsTable;
	}
	
	
	//--------------------------------Panes---------------------------------------------
	public Pane getCustomerEventPane(){
		VBox pane = new VBox();
		pane.getChildren().addAll(getTable());
		return pane;
	}
	
	
	
	
	//-----------------------Set Listener Methods---------------------------------
		public void setPane4EventListener(Pane4EventListener pane4EventListener) {
			System.out.println("in the setPane4EventListener method!");
			this.pane4EventListener = pane4EventListener;
		}

}
