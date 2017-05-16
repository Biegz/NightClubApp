package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import controller.tableEvents.MyEventsMenuEvent;
import controller.tableEvents.PastEvent;
import controller.tableEvents.UpcomingEvent;
import controller.tableEvents.ZipWithin15MenuEvent;
import controller.tableEvents.ZipWithin50MenuEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Event;
import model.EventsBag;
import model.Table;
import model.Ticket;
import model.model4Address.Address;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;
import view.MainMenu;
import view.MainWindow;
import view.Pane4Event;
import view.Pane4EventCreation;
import view.Pane4Events;
import view.Pane4Table;
import view.PaneForBusiness;
import view.PaneForCustomer;
import view.PaneForLogin;
import view.PrimaryView;

public class MenuController {
	private MainWindow mainView;
	private MainMenu view;
	private Pane4Table view2;
	private PaneForLogin view3;
	private Pane4Events view4;
	private Pane4EventCreation view5;
	private PaneForBusiness view6;
	

	private SignInUp signInUp;
	private Event modelEvent;
	private TableTranslator translator;
	private TableView<Event> table;
	private Business modelBusiness;
	private Customer modelCustomer;
	
	
	private TableController tableController;
	
	public MenuController(MainMenu view) {
		this.view = view;
		this.view2 = new Pane4Table();
		translator = new TableTranslator();
		tableController = new TableController(view2);
		
		view.setTableListener(new TableListener() {
			public void allEventsMenuClicked() {
				//TableTranslator translator = new TableTranslator();
				table = view2.getTable(translator.getAllEvents());
				displayAllEvents(table);
			}

			public void myEventsMenuClicked(MyEventsMenuEvent ev){
				modelBusiness = ev.getBusiness();
				//TableTranslator translator = new TableTranslator();
				table = view2.getTable(translator.getMyEvents(modelBusiness));
				displayMyEvents(table);
			}
			
			public void zipWithin15Clicked(ZipWithin15MenuEvent ev){
				modelCustomer = ev.getCustomer();
				//TableTranslator translator = new TableTranslator();
				table = view2.getTable(translator.getByZip15(modelCustomer));
				displayEventsWithin15Miles(table);
			}
			
			public void zipWithin50Clicked(ZipWithin50MenuEvent ev){
				modelCustomer = ev.getCustomer();
				//TableTranslator translator = new TableTranslator();
				table = view2.getTable(translator.getByZip50(modelCustomer));
				displayEventsWithin50Miles(table);
			}
			
			public void upcomingEventsClicked(UpcomingEvent ev) {
				modelCustomer = ev.getCustomer();
				view2.setMyEventsTable(translator.getMyUpcomingEvents(modelCustomer));
				Label upcoming = new Label("My Upcoming Events");
				displayEvents(view2.getMyEventsTable(), upcoming);
			}
			public void pastEventsClicked(PastEvent ev){
				modelCustomer = ev.getCustomer();
				view2.setMyEventsTable(translator.getMyPastEvents(modelCustomer));
				Label past = new Label("My Past Events");
				displayEvents(view2.getMyEventsTable(), past);
			}
			public void eventRecommendationLogin(){
				modelCustomer = Current.getCustomer();
				table = view2.getTable(translator.getRecommendedEvents(modelCustomer));
				displayREvents(table);
			}
			
			
		});
		
		
	}	
	


	public MenuController(SignInUp view3){
		this.signInUp = view3;
		this.view2 = new Pane4Table();
		translator = new TableTranslator();
		tableController = new TableController(view2);
		signInUp.setTableListener( new TableListener() {
			
			public void eventRecommendationLogin(){
				modelCustomer = Current.getCustomer();
				table = view2.getTable(translator.getRecommendedEvents(modelCustomer));
				displayREvents(table);
			}
			
			public void allEventsLogin() {
				
				table = view2.getTable(translator.getAllEvents());
				displayAllEvents(table);
				//PrimaryView.changePane(MainWindow.getCustomerWindow());
			}
		});
		
	}
	

	
	public MenuController(Pane4EventCreation view){
		this.view5 = view;
		
		view2 = new Pane4Table();
		translator = new TableTranslator();
		tableController = new TableController(view2);
		
		view.setTableListener(new TableListener(){
			public void createButtonClicked(CreateButtonEvent ev){
				modelEvent = ev.getEvent();
				

				modelEvent.setBusiness(ev.getEvent().getBusiness());
				modelEvent.setEventName(ev.getEvent().getEventName());
				modelEvent.setGenre(ev.getEvent().getGenre());
				modelEvent.setDescription(ev.getEvent().getDescription());

				modelEvent.setAddress(ev.getEvent().getAddress());

				// modelAddress.setCity(ev.getEvent().getAddress().getCity());
				// modelAddress.setState(ev.getEvent().getAddress().getState());
				// modelAddress.setStreet(ev.getEvent().getAddress().getStreet());
				// modelAddress.setNumber(ev.getEvent().getAddress().getNumber());
				// modelAddress.setZipcode(ev.getEvent().getAddress().getZipcode());
				// model.setAddress(modelAddress);

				modelEvent.setDate(ev.getEvent().getDate());
				modelEvent.setTotalTickets(ev.getEvent().getTotalTickets());
				modelEvent.setTicketPrice(ev.getEvent().getTicketPrice());
				modelEvent.setTotalTables(ev.getEvent().getTotalTables());
				modelEvent.setTablePrice(ev.getEvent().getTablePrice());
				
				EventsBag.add(modelEvent);
				EventsBag.save();
				
				modelBusiness = Current.getBusiness();
				table = view2.getTable(translator.getMyEvents(modelBusiness));
				displayMyEvents(table);
				
				emptyPane();
				IO.saveAll();
			
			}
			
			public void deleteButtonClicked(DeleteButtonEvent ev){
				modelEvent = ev.getEvent();
				EventsBag.delete(modelEvent);
				
				modelBusiness = Current.getBusiness();
				table = view2.getTable(translator.getMyEvents(modelBusiness));
				displayMyEvents(table);

				CustomerTicketProcessing ctp = new CustomerTicketProcessing(modelEvent);
				
				
				ArrayList<Customer> tempCustomerList = modelEvent.getCustomerList();
				for (Iterator<Customer> iterator = tempCustomerList.iterator(); iterator.hasNext();) {
					Customer tempCustomer = iterator.next();
					
					ArrayList<Ticket> tempTicketList = tempCustomer.getTicketList();
					for (Iterator<Ticket> ticketIt = tempTicketList.iterator(); iterator.hasNext();) {	
						Ticket tempTicket = ticketIt.next();
							if (tempTicket.getEventName().equals(modelEvent.getEventName())){
							ctp.returnTicket(tempTicket, tempCustomer);
							ticketIt.remove();

						}
					}
					
					ArrayList<Table> tempTableList = tempCustomer.getTableList();
					for (Iterator<Table> tableIt = tempTableList.iterator(); iterator.hasNext();) {	
						Table tempTable = tableIt.next();
							if (tempTable.getEventName().equals(modelEvent.getEventName())){
							ctp.returnTable(tempTable, tempCustomer);
							tableIt.remove();
						}
					}
						tempCustomer.removeEvent(modelEvent);
						iterator.remove();
					}
 
				
				emptyPane();
				IO.saveAll();
			}
			
			public void updateButtonClicked(UpdateButtonEvent ev){
				modelEvent = ev.getEvent();
				
				modelEvent.setGenre(ev.getEvent().getGenre());
				modelEvent.setDescription(ev.getEvent().getDescription());

				modelEvent.setAddress(ev.getEvent().getAddress());
				
				modelEvent.setTotalTickets(ev.getEvent().getTotalTickets());
				modelEvent.setTicketPrice(ev.getEvent().getTicketPrice());
				modelEvent.setTotalTables(ev.getEvent().getTotalTables());
				modelEvent.setTablePrice(ev.getEvent().getTablePrice());
				
				//EventsBag.add(modelEvent);
				EventsBag.save();
				
				modelBusiness = Current.getBusiness();
				table = view2.getTable(translator.getMyEvents(modelBusiness));
				displayMyEvents(table);
				
				emptyPane();
				IO.saveAll();
				
			}
			
			
		});
		
		
	}
	
	public MenuController(Pane4Events view4){
		this.view4 = view4;
		this.view2 = new Pane4Table();
		translator = new TableTranslator();
		tableController = new TableController(view2);
		
		view4.setTableListener(new TableListener(){
			public void searchButtonClicked(SearchButtonEvent ev){
				ArrayList<Event> temp = new ArrayList<>();
				for(Event e: EventsBag.events){
					if(e.getDate().isAfter(LocalDate.now())){
						if(ev.getVenueSearch().equals(e.getBusiness().getName())){
							temp.add(e);
						}else if(ev.getVenueSearch().equals(e.getAddress().getZipcode())){
							temp.add(e);
						}
					}
					
				}
				
				table = view2.getTable(temp);
				displayEventsByVenue(table);
				
			}
			
		});
	}
	
	public void displayEventsByVenue(Node temp){
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		Label header = new Label("This Venue's Events:");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, temp);
		

		MainWindow.setLeft(pane);
	}
	
	public void displayAllEvents(Node temp) {
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		Label header = new Label("All Events");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, temp);
		
		MainWindow.setLeft(pane);
		MainWindow.setCenter(null);
	}
	
	public void displayREvents(Node temp) {
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		Label header = new Label("Recommended Events");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, temp);
		
		MainWindow.setLeft(pane);
		MainWindow.setRight(null);
		MainWindow.setBottom(null);
		MainWindow.setCenter(null);
	}
	
	public void displayMyEvents(Node temp){
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		Label header = new Label("My Events");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, temp);
		

		MainWindow.setLeft(pane);
		MainWindow.setCenter(null);
	}
	
	public void displayEventsWithin15Miles(Node temp){
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		Label header = new Label("Events Within 15 Miles*");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, temp);
		

		MainWindow.setLeft(pane);
		MainWindow.setCenter(null);
	}
	
	public void displayEventsWithin50Miles(Node temp){
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		Label header = new Label("Events WIthin 50 Miles*");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, temp);
		

		MainWindow.setLeft(pane);
		MainWindow.setCenter(null);
	}
	
	public void displayEvents(Node temp, Label temp2) {
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		
		temp2.setFont(new Font(32));
		headerPane.getChildren().addAll(temp2);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, temp);
		
		
		MainWindow.setLeft(pane);
		MainWindow.setRight(null);
	}
	
	public void emptyPane() {
		MainWindow.setCenter(null);
	}
	


	
	
}