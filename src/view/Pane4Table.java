package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.ClickEventEvent;
import controller.Current;
import controller.Pane4EventEvent;
import controller.Pane4EventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
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
	private TableView<Event> myEventsTable;
	private TableView<Event> eventsTable;
	private ObservableList<Event> events;
	private ObservableList<Event> myEvents;	
	private ArrayList<Event> temp;

	private HBox pane;

	private Pane4EventListener pane4EventListener;
	

	public Pane4Table(){
		
	}
	
	
	
//	public ObservableList<Event> getTableFilter(){
//		return events;
//	}
	
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
	public TableView getTable(ArrayList<Event> temp) {
		events = FXCollections.observableArrayList(temp);

		eventsTable = new TableView<Event>();

		eventsTable.setEditable(false);
		eventsTable.setMaxHeight(400);
		eventsTable.setMaxWidth(250);
		eventsTable.setPlaceholder(new Label("No Events"));

		eventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		eventsTable.getColumns().addAll(getDateColumn(), getEventNameColumn());
		eventsTable.setItems(events);

		eventsTable.setRowFactory(e -> {
			TableRow<Event> row = new TableRow<Event>();
			row.setOnMouseClicked(ev -> {
				if (ev.getClickCount() >= 1 && (!row.isEmpty())) {
					Event rowData = row.getItem();
					Pane4EventEvent ev3 = new Pane4EventEvent(this, rowData);
					if (pane4EventListener != null) {
						pane4EventListener.rowSelected(ev3);
					}
				}
			});
			return row;
		});
		return eventsTable;
	}
	
	public void setMyEventsTable(ArrayList<Event> temp) {
		myEvents = FXCollections.observableArrayList(temp);
		myEventsTable = new TableView<Event>();
		myEventsTable.setEditable(false);
		myEventsTable.setMaxHeight(400);
		myEventsTable.setMaxWidth(250);
		myEventsTable.setPlaceholder(new Label("No Events"));
		myEventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		myEventsTable.getColumns().addAll(getDateColumn(), getEventNameColumn());//, getCostColumn());
		myEventsTable.setItems(myEvents);
	}
	
	public TableView getMyEventsTable(){
		myEventsTable.setRowFactory(e ->{
			TableRow<Event> row = new TableRow<Event>();
			row.setOnMouseClicked(ev ->{
				if(ev.getClickCount() >= 1 && (!row.isEmpty())){  //set so if you double click it will delete that event, will change to delete event button soon
					Event rowData = row.getItem();
					ClickEventEvent clickEvent = new ClickEventEvent(this,Current.getCustomer(), rowData);
					if (pane4EventListener != null) {
						pane4EventListener.eventRowSelected(clickEvent);
					}
				}
			});
			return row;
		});
		return myEventsTable;
	}
	
	//-----------------------Set Listener Methods---------------------------------
		public void setPane4EventListener(Pane4EventListener pane4EventListener) {
			this.pane4EventListener = pane4EventListener;
		}

}
