package view;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

import controller.CreateButtonEvent;
import controller.Current;
import controller.DeleteButtonEvent;
import controller.EventController;
import controller.EventsListener;
import controller.Pane4EventEvent;
import controller.TableListener;
import controller.UpdateButtonEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Event;
import model.EventsBag;
import model.Genre;
import model.model4Address.Address;

public class Pane4EventCreation {


	private Button createEventButton;
	private Button deleteEventButton;
	private Button updateEventButton;
	private TableListener tableListener;

	private Label errorLabel = new Label();
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
	
	public VBox getCreatePane(Node n1) {
		VBox createView = new VBox(5);
		
		createView.setPadding(new Insets(5,0,0,0));
		createView.getChildren().addAll(name(),description(),date(),address(),cityStateZip(),genre(),ticketPrice(),tablePrice(),totalTables(),totalTickets(), getCreateEventButton(), n1 );
		return createView;
	}
	
	public VBox getUpdatePane(){
		 VBox editView = new VBox(5);
			editView.setPadding(new Insets(5,0,0,0));

		editView.getChildren().addAll(description(),address(),cityStateZip(),genre(),ticketPrice(),tablePrice(),totalTables(),totalTickets(), getUpdateEventButton(), getDeleteEventButton());
		return editView;
	}
	
	
	//--------------------------Capture Buttons------------------------------------------------------

	public Button getCreateEventButton(){

		createEventButton = new Button("Create Event");
		createEventButton.setOnAction(e ->{
			
			      if(getName().isEmpty()){
				    MainWindow.setCenter(getCreatePane(new Label("Enter a name!")));
				    
			    } else if(getDate().isBefore(LocalDate.now())){
					MainWindow.setCenter(getCreatePane(new Label("Enter a future date!")));
					
				} else if(getAddress().isEmpty() || getCity().isEmpty() || getZip().isEmpty()){
					MainWindow.setCenter(getCreatePane(new Label("Enter address info!")));
					
				} else if(getTicketPrice() + getTablePrice() + getTotalTickets() + getTotalTables() <= 0){
					MainWindow.setCenter(getCreatePane(new Label("Prices and Quantities must be greater than zero!")));
					
				} else if (testDoubles(ticketPriceField) || testDoubles(tablePriceField) 
						|| testInts(totalTicketsField) || testInts(totalTablesField)){
					MainWindow.setCenter(getCreatePane(new Label("Price and Quantities Must Be Numbers!")));
				}
			      
				 else {
					CreateButtonEvent ev = new CreateButtonEvent(this, new Event(
							Current.getBusiness(),
							getName(), getGenre(), getDescription(), new Address(getAddress(), null, getZip(), getState(), getCity()),
							getDate(), getTotalTickets(), getTicketPrice(), getTotalTables(), getTablePrice()));
					if(tableListener != null){
						tableListener.createButtonClicked(ev);
					}
				}
			      
			
		});
		return createEventButton;
		
	}
	
	private boolean testInts(TextField text){
		try {
			Integer.parseInt(text.getText());
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}
	
	private boolean testDoubles(TextField text){
		try{
			Double.parseDouble(text.getText());
		}catch(NumberFormatException e){
			return true;
		}
		return false;
	}

	public Button getDeleteEventButton(){
		deleteEventButton = new Button("Cancel Event");
		deleteEventButton.setOnAction(e ->{
			DeleteButtonEvent ev = new DeleteButtonEvent(this, Current.getEvent());	
			if(tableListener != null){
				tableListener.deleteButtonClicked(ev);
			}
		});
		
		return deleteEventButton;
		
	}
	
	public Button getUpdateEventButton(){
		updateEventButton = new Button("Update Event");
     	updateEventButton.setOnAction(e ->{
			UpdateButtonEvent ev = new UpdateButtonEvent(this, Current.getEvent());
			
			if(tableListener != null){
				tableListener.updateButtonClicked(ev);
			}
			System.out.println("I can create an event!");
		});
		return updateEventButton;
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
		dateField.setValue(LocalDate.now().plusWeeks(1));
		date.getChildren().addAll(dateLabel, dateField);
		return date;
	}
	
	public HBox address(){
		HBox address = new HBox(5);
		Label addressLabel = new Label("Address:");
		addressField = new TextField();
		addressField.setPromptText("Event location"); //(Current.getBusiness().getAddress().getNumber() + Current.getBusiness().getAddress().getStreet() );
		address.getChildren().addAll(addressLabel, addressField);
		return address;
	}
	
	public VBox cityStateZip() {
		VBox pane = new VBox();
		HBox pane2 = new HBox();
		HBox cityStateZip = new HBox(5);
		Label cityLabel = new Label("City:");
		Label stateLabel = new Label("State:");
		Label zipLabel = new Label("Zip Code:");
		cityField = new TextField();
		stateField = new ComboBox();
		stateField.getItems().addAll(stateList);
		stateField.getSelectionModel().select("New York"); 
		zipField = new TextField();
		pane2.getChildren().addAll(zipLabel, zipField );
		cityStateZip.getChildren().addAll(cityLabel, cityField, stateLabel, stateField);
		pane.getChildren().addAll(cityStateZip, pane2);
		pane.setSpacing(5);
		return pane;
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
	
	
	

	
	
	
	public void setTableListener(TableListener menu){
		this.tableListener = menu;
	}
	

}
