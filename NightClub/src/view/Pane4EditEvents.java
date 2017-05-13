package view;

import controller.CreateButtonEvent;
import controller.Current;
import controller.EventsListener;
import controller.Pane4EventListener;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Event;
import model.EventsBag;
import model.model4Address.Address;

public class Pane4EditEvents {

	private MainWindow mainWindow;
	private Pane4EventCreation p;
	private Button createEventButton;
	private Button deleteEventButton;
	private Button updateEventButton;
	private EventsBag eventsBag = new EventsBag();

	private HBox pane;
	private Pane4Event paneEv;

	private Pane4EventListener pane4EventListener;
	
	private EventsListener eventsListener;

	
	
	private Current c = new Current();
	
	

	public Pane4EditEvents() {
		pane = new HBox();
	}
	
	public Pane getButtons(){
		pane.getChildren().addAll(getAddButton(), getDeleteButton());
		return pane;

	}

	//--------------------------------Show Buttons---------------------------------------------------
	
	
	public Button getAddButton(){
		Button addButton = new Button("+");
		Pane4EventCreation pane4EventCreation = new Pane4EventCreation();
		addButton.setOnAction(e ->{
			mainWindow.setCenter(pane4EventCreation.getCreatePane()); //take out street number field from address cause its 
		});
		return addButton;
	}
	
	public Button getDeleteButton(){
		Button deleteButton = new Button("-");
		Pane4EventCreation pane4EventCreation = new Pane4EventCreation();
		
		deleteButton.setOnAction(e -> {
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
		//myEvents = FXCollections.observableArrayList(EventsBag.events);//Could not get the current business' events list to print (tried getEventsList from business model)

		
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
			
//			eventsBag.add(ev.getEvent());
//			eventsBag.save();
//			
			

		});
		return createEventButton;
		
	}
	
	public Button getDeleteEventButton(){
		deleteEventButton = new Button("Cancel Event");
		deleteEventButton.setOnAction(e ->{
//			DeleteButtonEvent ev = new DeleteButtonEvent();	
//			System.out.println("Is null");
//			if(eventsListener != null){
//				System.out.println("Not null");
//				eventsListener.deleteButtonClicked(ev);
//			}
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
		pane.getChildren().addAll(getSearchBox());
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
	
	
}
