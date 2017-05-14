package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

import controller.CreateButtonEvent;
import controller.Current;
import controller.EventController;
import controller.EventsListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Event;
import model.EventsBag;
import model.Genre;
import model.model4Address.Address;

public class Pane4EventCreation {


	
	private Button createEventButton;
	private EventsListener eventsListener;

	private Pane eventCreationPane;
	public static TextField nameField;
	public static TextField descriptionField;
	public static DatePicker dateField;
	public static ComboBox<Genre> genreField;
	public static TextField addressField;
	public static TextField cityField;
	public static ComboBox stateField;
	public static TextField zipField;
	public static TextField ticketPriceField;
	public static TextField tablePriceField;
	public static TextField totalTicketsField;
	public static TextField totalTablesField;
	String[] stateArr = new String[]{
	        "Alabama", "Alaska", "Arizona", "Arkansas", "California",
	        "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
	        "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
	        "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
	        "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
	        "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
	        "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
	        "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
	        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
	        "Wisconsin", "Wyoming"
	    	};
	List<String> stateList = new ArrayList<String>(Arrays.asList(stateArr));
	public Pane4EventCreation(){
		eventCreationPane = new Pane(); 
		
	}
	
	
	//-----------------------------Data Panes--------------------------------------------------------------
	
	public VBox getCreatePane() {
		// Pane4EditEvents pane4EditEvents = new Pane4EditEvents();
		 //EventController controller = new EventController(pane4EditEvents);
		

		VBox updateView = new VBox(5);
		updateView.getChildren().addAll(name(),description(),date(),address(),cityStateZip(),genre(),ticketPrice(),tablePrice(),totalTables(),totalTickets(), getCreateEventButton());
		return updateView;
	}
	
	public VBox getUpdatePane(){
		 Pane4EditEvents pane4EditEvents = new Pane4EditEvents();
		 //EventController controller = new EventController(pane4EditEvents);		
		 VBox editView = new VBox();
		editView.getChildren().addAll(name(),description(),date(),address(),cityStateZip(),genre(),ticketPrice(),tablePrice(),totalTables(),totalTickets(), pane4EditEvents.getUpdateEventButton());
		return editView;
	}
	
	public VBox getDeletePane(){
		 Pane4EditEvents pane4EditEvents = new Pane4EditEvents();
		 //EventController controller = new EventController(pane4EditEvents);
		VBox deleteView = new VBox();
		Label cancelLbl = new Label("*Select the event you would like to cancel on the table, then press 'Cancel Event' below*");
		deleteView.getChildren().addAll(cancelLbl, pane4EditEvents.getDeleteEventButton());

		return deleteView;
	}
	

	
	
	
	public Button getCreateEventButton(){
		//myEvents = FXCollections.observableArrayList(EventsBag.events);//Could not get the current business' events list to print (tried getEventsList from business model)

		createEventButton = new Button("Create Event");
		createEventButton.setOnAction(e ->{
			CreateButtonEvent ev = new CreateButtonEvent(this, new Event(
					Current.getBusiness(),
					getName(), getGenre(), getDescription(), new Address(getAddress(), null, getZip(), getState(), getCity()),
					getDate(), getTotalTickets(), getTicketPrice(), getTotalTables(), getTablePrice()));
			if(eventsListener != null){
				System.out.println("Hit the if statement within getCreateEventButton method!");
				eventsListener.createButtonClicked(ev);
			}
			
			EventsBag.add(ev.getEvent());
			EventsBag.save();
			
			

		});
		return createEventButton;
		
	}
	
	
	
	
	
	//-------------------------------------data------------------------------------------------------------
	
	public HBox name(){
		HBox name = new HBox(5);
		Label nameLabel = new Label(" Name:");
		nameField = new TextField();
		name.getChildren().addAll(nameLabel, nameField);
		return name;
	}
	
	public HBox description(){
		HBox description = new HBox(5);
		Label descriptionLabel = new Label("Description:");
		descriptionField = new TextField();
		description.getChildren().addAll(descriptionLabel, descriptionField);
		return description;
	}
	
	public HBox genre(){
		HBox genre = new HBox(5);
		Label genreLabel = new Label("Genre:");
		genreField = new ComboBox<>();
		genreField.getItems().addAll(Genre.values());
		genre.getChildren().addAll(genreLabel, genreField);
		return genre;
	}
	
	public HBox date(){
		HBox date = new HBox(5);
		Label dateLabel = new Label("Date:");
		dateField = new DatePicker();
		dateField.setPromptText("Select a date");
		date.getChildren().addAll(dateLabel, dateField);
		return date;
	}
	
	public HBox address(){
		HBox address = new HBox(5);
		Label addressLabel = new Label("Address:");
		addressField = new TextField();
		addressField.setText("Current Business's Address"); //(Current.getBusiness().getAddress().getNumber() + Current.getBusiness().getAddress().getStreet() );
		address.getChildren().addAll(addressLabel, addressField);
		return address;
	}
	
	public HBox cityStateZip() {
		HBox cityStateZip = new HBox(5);
		Label cityLabel = new Label("City:");
		Label stateLabel = new Label("State:");
		Label zipLabel = new Label("Zip Code:");
		cityField = new TextField();
		cityField.setText("Current Business's City"); // (Current.getBusiness().getAddress().getCity());
		stateField = new ComboBox();
		stateField.getItems().addAll(stateList);
		stateField.getSelectionModel().select("New York"); //Current.getBusiness().getAddress().getCity());
		stateField.setPromptText("State");
		zipField = new TextField();
		zipField.setText("Current Business's Zip"); // (Current.getBusiness().getAddress().getZip());
		cityStateZip.getChildren().addAll(cityLabel, cityField, stateLabel, stateField, zipLabel, zipField);
		return cityStateZip;
	}
	
	public HBox ticketPrice(){
		HBox ticketPrice = new HBox(5);
		Label ticketPriceLabel = new Label("Ticket Price:");
		ticketPriceField = new TextField();
		ticketPrice.getChildren().addAll(ticketPriceLabel, ticketPriceField);
		return ticketPrice;
	}
	
	public HBox tablePrice(){
		HBox tablePrice = new HBox(5);
		Label tablePriceLabel = new Label("Table Price:");
		tablePriceField = new TextField();
		tablePrice.getChildren().addAll(tablePriceLabel, tablePriceField);
		return tablePrice;
	}
	
	public HBox totalTickets(){
		HBox totalTickets = new HBox(5);
		Label totalTicketsLabel = new Label("Tickets Available:");
		totalTicketsField = new TextField();
		totalTickets.getChildren().addAll(totalTicketsLabel, totalTicketsField);
		return totalTickets;
	}
	
	public HBox totalTables(){
		HBox totalTables = new HBox(5);
		Label totalTablesLabel = new Label("Tables Available:");
		totalTablesField = new TextField();
		totalTables.getChildren().addAll(totalTablesLabel, totalTablesField);
		return totalTables;
	}
	
	
	
	
	//---------------------------------getters-----------------------------------------
	
	public String getName(){
		return nameField.getText();
	}
	
	public Genre getGenre(){
		return genreField.getSelectionModel().getSelectedItem();
	}
	
	public String getDescription(){
		return descriptionField.getText();
	}
	
	public LocalDate getDate(){
		return dateField.getValue();
	}
	
	public String getAddress(){
		return addressField.getText();
	}
	
	public String getCity(){
		return cityField.getText();
	}
	
	public String getState(){
		return stateField.getPromptText();
	}
	
	public String getZip(){
		return zipField.getText();
	}
	
	public Double getTicketPrice(){
		return Double.parseDouble(ticketPriceField.getText());
	}
	
	public Double getTablePrice(){
		return Double.parseDouble(tablePriceField.getText());
	}
	
	public int getTotalTickets(){
		return Integer.parseInt(totalTicketsField.getText());
	}
	
	public int getTotalTables(){
		return Integer.parseInt(totalTablesField.getText());
	}
	
	
	public void setEventsListener(EventsListener eventsListener) {
		System.out.println("Hit the setCreateEventListener method!");
		this.eventsListener = eventsListener;
	}
	

}
