package view;

import javafx.scene.control.Label;



import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.CreateButtonEvent;
import controller.Current;
import controller.DeleteButtonEvent;
import controller.EventsListener;
import controller.Pane4EventEvent;
import controller.Pane4EventListener;
import controller.UpdateButtonEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.model4User.model4Establishment.Business;
import model.Event;
import model.EventsBag;
import model.model4Address.Address;
import model.model4User.User;
import model.model4User.model4Customer.Customer;

public class Pane4Events {
	
	private MainWindow mainWindow;
	private Pane4EventCreation p;
	private Button createEventButton;
	private Button deleteEventButton;
	private Button updateEventButton;
	
	
	
	

	private TableView<Event> eventsTable;
	private TableView<Event> myEventsTable;
	
	private EventsBag eventsBag = new EventsBag();
	private ObservableList<Event> myEvents;
	private ObservableList<Event> events;
	private HBox pane;
	private Pane4Event paneEv;

	private Pane4EventListener pane4EventListener;
	
	private EventsListener eventsListener;

	
	
	private Current c = new Current();
	
	

	public Pane4Events() {
		pane = new HBox();
	}
	
	public Pane getButtons(){
		pane.getChildren().addAll(getAddButton(), getDeleteButton());
		return pane;

	}
	
	
	
	
	
	//------------------------------------Columns--------------------------------------------------

	public TableColumn getDateColumn() {
		TableColumn dateColumn = new TableColumn("Date");
		//dateColumn.impl_setWidth(95);
		dateColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("date"));
		return dateColumn;
	}

	public TableColumn getEventNameColumn() {
		TableColumn eventNameColumn = new TableColumn("Event Name");
		//eventNameColumn.impl_setWidth(155);
		eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
		return eventNameColumn;
	}
	
	
	
	
	
	//---------------------------------Tables-----------------------------------------------------
	
	
	public TableView getMyEventsTable(){
		myEvents = FXCollections.observableArrayList(EventsBag.getCurrentBusinessEvents());
		
		myEventsTable = new TableView<Event>();
		myEventsTable.setEditable(false);
		myEventsTable.setMaxHeight(400);
		myEventsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		myEventsTable.getColumns().addAll(getDateColumn(), getEventNameColumn());
		myEventsTable.setItems(myEvents);
		
		
		myEventsTable.setRowFactory(e ->{
			
			TableRow<Event> row = new TableRow<Event>();
			row.setOnMouseClicked(ev ->{
				if(ev.getClickCount() >= 1 && (!row.isEmpty())){  //set so if you double click it will delete that event, will change to delete event button soon
					System.out.println("Detected clicks");
					Event rowData = row.getItem();
					Current.setEvent(rowData);
					Pane4EventEvent ev2 = new Pane4EventEvent(this, rowData);
					if(pane4EventListener != null){
						System.out.println("In the if statement in myEventsTable");
						pane4EventListener.rowSelected(ev2);
					}	
//					eventsBag.delete(ev2.getEvent());
//					eventsBag.save();
					

					

				}
			});
			return row;
		});
		
		
			
		
		
		
		
		return myEventsTable;

		
	}

	public TableView getTable() {
		events = FXCollections.observableArrayList(EventsBag.events);
		eventsTable = new TableView<Event>();

		eventsTable.setEditable(false);
		eventsTable.setMaxHeight(400);
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
	
	
	
	
	
	//--------------------------------Show Buttons---------------------------------------------------
	
	
	public Button getAddButton(){
		Button addButton = new Button("+");
		Pane4EventCreation pane4EventCreation = new Pane4EventCreation();
		addButton.setOnAction(e ->{
			mainWindow.setCenter(pane4EventCreation.getCreatePane()); //take out street number field from address cause its wack
		});
		return addButton;
	}
	
	public Button getDeleteButton(){
		Button deleteButton = new Button("-");
		Pane4EventCreation pane4EventCreation = new Pane4EventCreation();
		
		deleteButton.setOnAction(e -> {
			mainWindow.setCenter(pane4EventCreation.getDeletePane());
		});
		return deleteButton;
	}
	

	public Button getUpdateButton(){
		Pane4EventCreation pane4EventCreation = new Pane4EventCreation();
		Button updateButton = new Button("Update");
		updateButton.setOnAction(e ->{
			mainWindow.setCenter(pane4EventCreation.getUpdatePane()); //take out street number field from address cause its wack

		});
		
		return updateButton;
	}
	
	public Button getRefreshButton(){
		EstablishmentHLPane establishmentHLPane = new EstablishmentHLPane();
		Button refreshButton = new Button("Refresh");
		
		refreshButton.setOnAction(e ->{
		
		});
		
		return refreshButton;
	}
	
	
	
	//-------------------------------Capture buttons------------------------------------------------------

	
	public Button getCreateEventButton(){
		myEvents = FXCollections.observableArrayList(EventsBag.events);//Could not get the current business' events list to print (tried getEventsList from business model)

		
		p = new Pane4EventCreation();
		createEventButton = new Button("Create Event");
		createEventButton.setOnAction(e ->{
			CreateButtonEvent ev = new CreateButtonEvent(this, new Event(
					Current.getBusiness(),
					p.getName(), p.getGenre(), p.getDescription(), new Address(p.getAddress(), null, p.getZip(), p.getState(), p.getCity()),
					p.getDate(), p.getTotalTickets(), p.getTicketPrice(), p.getTotalTables(), p.getTablePrice()));
			if(eventsListener != null){
				System.out.println("Hit the if statement within getCreateEventButton method!");
				eventsListener.createButtonClicked(ev);
			}
			
			eventsBag.add(ev.getEvent());
			eventsBag.save();
			
			

		});
		return createEventButton;
		
	}
	
	public Button getDeleteEventButton(){
		deleteEventButton = new Button("Cancel Event");
		deleteEventButton.setOnAction(e ->{
			DeleteButtonEvent ev = new DeleteButtonEvent(this, ((Event) this.getMyEventsTable().getSelectionModel().getSelectedItem()));	
			System.out.println("Is null");
			
			if(eventsListener != null){
				System.out.println("Not null");
				eventsListener.deleteButtonClicked(ev);
			}
		});
		
		return deleteEventButton;
		
	}
	
	public Button getUpdateEventButton(){
		updateEventButton = new Button("Update Event");
     	updateEventButton.setOnAction(e ->{
//			UpdateButtonEvent ev = new UpdateButtonEvent(e);
//			
//			if(eventsListener != null){
//				System.out.println("not null");
//				eventsListener.updateButtonClicked(ev);
//			}
			System.out.println("I can create an event!");
		});
		return updateEventButton;
	}
	

	
	public Button getSearchButton(){
		Button searchButton = new Button("Search");
		searchButton.setOnAction(e ->{
			
		});
		return searchButton;
	}
	
	
	
//------------------------Panes---------------------------------------------------	
	
	
	public Pane getSearchBox(){
		
		HBox pane = new HBox();
		HBox pane2 = new HBox();
		HBox pane3 = new HBox();
		VBox mainPane = new VBox();
		
		
		
		Label searchLbl = new Label("Search Club by Zipcode:");
		TextField searchTF = new TextField("Zipcode");
		
		
		pane.getChildren().addAll(searchLbl);
		pane2.getChildren().addAll(searchTF);
		pane3.getChildren().addAll(getSearchButton());
		mainPane.getChildren().addAll(pane, pane2, pane3);
		
		return mainPane;
	}
	
	
	
	
	public Pane getCustomerEventPane(){
		VBox pane = new VBox();
		pane.getChildren().addAll(getTable(),getSearchBox());
		return pane;
	}
	
	
	
//-----------------------Set Listener Methods---------------------------------
	
	
	

	public void setPane4EventListener(Pane4EventListener pane4EventListener) {
		System.out.println("in the setPane4EventListener method!");
		this.pane4EventListener = pane4EventListener;
	}

	public void setEventsListener(EventsListener eventsListener) {
		System.out.println("Hit the setCreateEventListener method!");
		this.eventsListener = eventsListener;
	}
	
	

	// have all objects from events bag(ArrayList) print out into their own row
	// // in the table, with only the date
	// field and event name displayed. When the user double clicks on the row,
	// // have a more information pane
	// pop up with the rest of the fields of that event. A buy ticket button //
	// will be displayed under that in which
	// the user can choose to buy one or more of the tickets to that event.
}
