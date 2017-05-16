package view;

import controller.Current;
import controller.CustomerTicketProcessing;
import controller.CustomerAccountController;
import controller.MenuController;
import controller.TableListener;
import controller.TableTranslator;
import controller.tableEvents.MyEventsMenuEvent;
import controller.tableEvents.PastEvent;
import controller.tableEvents.UpcomingEvent;
import controller.tableEvents.ZipWithin15MenuEvent;
import controller.tableEvents.ZipWithin50MenuEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenu {

	private final String house = "\u2302";
	private final String gear = "\u26ed";
	
	private TableListener tableListener;
	private Pane4EventCreation pane4EventCreation;
	public MainMenu() {

	}

	// Menu Bars
	public MenuBar getCustomerMenuBar() {
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(customerHome(), getCustomerEvents(), customerSettings());
		return menuBar;
	}

	public MenuBar getBusinessMenuBar() {
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(businessHome(), getBusinessEvents(), businessSettings());
		return menuBar;
	}

	// Menus
	private Menu customerHome() {
		Menu home = new Menu("Home");
		home.setId("menuText");
		home.getItems().addAll(customerGoHome());
		return home;
	}

	private Menu businessHome() {
		Menu home = new Menu("Home");
		home.getItems().addAll(businessGoHome());
		return home;
	}

	private Menu customerMenu() {
		Menu menu = new Menu("Menu");
		menu.getItems().addAll(displayUsers(), displayEvents());
		return menu;
	}

	private Menu businessMenu() {
		Menu menu = new Menu("Menu");
		menu.getItems().addAll(displayUsers(), displayEvents());
		return menu;
	}

	private Menu customerSettings() {
		Menu settings = new Menu("Settings");
		settings.getItems().addAll(customerEditAccount(), simulate(), logOut());
		return settings;
	}

	private Menu businessSettings() {
		Menu settings = new Menu("Settings");
		settings.getItems().addAll(businessEditAccount(), logOut());
		return settings;
	}

	private Menu getBusinessEvents() {
		Menu events = new Menu("Events");
		events.getItems().addAll(getAllEvents(), getMyEvents(), getCreateEvent());
		return events;
	}
	
	private Menu getCustomerEvents(){
		Menu events = new Menu("Events");
		events.getItems().addAll(getAllEvents(),getNearMe(),getSearchByVenue());
		return events;
	}
	
	
	
	//-------------------------Menu Items------------------------------------

	private MenuItem simulate(){
		MenuItem simulate = new MenuItem("  Simulate Ticket Being Bought ");

		simulate.setOnAction(e -> {
			CustomerTicketProcessing pro = new CustomerTicketProcessing(Current.getEvent());
			pro.buyTicket(1, Current.getCustomer(), Current.getEvent(), Current.getEvent().getBusiness());
		});

		return simulate;
	}
	
	private MenuItem logOut() {
		MenuItem logOut = new MenuItem("  Log Out ");

		logOut.setOnAction(e -> {
			PaneForLogin login = new PaneForLogin();
			PrimaryView.anchor(login.getPane());
		});

		return logOut;
	}
	
	private MenuItem displayUsers() {
		MenuItem displayUsers = new MenuItem("  Display Users ");

		displayUsers.setOnAction(e -> {
			PaneDisplayAllUsers users = new PaneDisplayAllUsers();
			MainWindow.setCenter(users.getPane());
		});

		return displayUsers;
	}

	private MenuItem displayEvents() {
		MenuItem displayEvents = new MenuItem("  Display Events ");

		displayEvents.setOnAction(e -> {
			PaneDisplayAllEvents events = new PaneDisplayAllEvents();
			MainWindow.setCenter(events.getPane());
		});

		return displayEvents;
	}
	
	private MenuItem getAllEvents() {

		MenuItem allEvents = new MenuItem("  All Events ");
		
		
		allEvents.setOnAction(e ->{
			if(tableListener!= null){
				tableListener.allEventsMenuClicked();
			}
		});
		return allEvents;
		
	}
	
	//-----------------------Menu Items For Customer----------------------------------------

	private MenuItem customerEditAccount() {
		CustomerHLPane pane = new CustomerHLPane();
		CustomerAccountController controller = new CustomerAccountController(pane);
		MenuItem editAccount = new MenuItem("  My Account ");

		editAccount.setOnAction(e -> {
			MainWindow.setCenter(pane.getHyperlinkPane());
		});

		return editAccount;
	}

	private MenuItem customerGoHome() {
		MenuItem goHome = new MenuItem("  Main Window ");

		goHome.setOnAction(e -> {

			if(tableListener!= null){
				tableListener.eventRecommendationLogin();
			}
		});

		return goHome;
	}

	private Menu getNearMe(){
		
		Menu nearMe = new Menu("  Near Me ");
		nearMe.getItems().addAll(getWithin15Miles(), getWithin50Miles());
		return nearMe;
	}
	
	private MenuItem getWithin15Miles(){
		
		MenuItem within15 = new MenuItem("  Within 15 Miles ");
		within15.setOnAction(e ->{
			ZipWithin15MenuEvent ev = new ZipWithin15MenuEvent(this, Current.getCustomer());
			if(tableListener!= null){
				tableListener.zipWithin15Clicked(ev);
			}
		});
		
		return within15;
	}
	
	private MenuItem getWithin50Miles(){
		
		MenuItem within50 = new MenuItem("  Within 50 Miles ");
		within50.setOnAction(e ->{
			ZipWithin50MenuEvent ev = new ZipWithin50MenuEvent(this, Current.getCustomer());
			if(tableListener!= null){
				tableListener.zipWithin50Clicked(ev);	
			}
				});
		
		
		
		return within50;
	}
	
	private MenuItem getSearchByVenue(){
		Pane4Events events = new Pane4Events();
		MenuController controller = new MenuController(events);
		MenuItem searchByVenue = new MenuItem("  Search By Venue/Zipcode ");
		searchByVenue.setOnAction(e ->{
			MainWindow.setCenter(events.getSearchByVenuePane());
		});
		return searchByVenue;
	}
	
	private Menu getMyCustomerEvents(){
		Menu getMyCustomerEvents = new Menu("  My Events ");
		getMyCustomerEvents.getItems().addAll(getUpcomingEvents(), getPastEvents());
		return getMyCustomerEvents;
	}
	
	private MenuItem getUpcomingEvents(){

		MenuItem searchByVenue = new MenuItem("  My Upcoming Events ");
		searchByVenue.setOnAction(e ->{
			UpcomingEvent ev = new UpcomingEvent(this, Current.getCustomer());
			if(tableListener!= null){
				tableListener.upcomingEventsClicked(ev);
			}
		});
		return searchByVenue;
	}
	

	private MenuItem getPastEvents(){

		MenuItem searchByVenue = new MenuItem("  My Past Events ");
		searchByVenue.setOnAction(e ->{

			PastEvent ev = new PastEvent(this, Current.getCustomer());
			if(tableListener!= null){
				tableListener.pastEventsClicked(ev);
			}
		});
		return searchByVenue;
	}
	
	
	
	
	
	//-----------------------------Menu Items for Business-----------------------


	private MenuItem businessEditAccount() {
		MenuItem editAccount = new MenuItem("  My Account ");
		
		EstablishmentHLPane pane = new EstablishmentHLPane();
		MenuController controller = new MenuController(pane);

		editAccount.setOnAction(e -> {
			MainWindow.setCenter(pane.getHyperlinkPane());
		});

		return editAccount;
	}
	
	private MenuItem businessGoHome() {
		MenuItem goHome = new MenuItem("  Main Window ");

		goHome.setOnAction(e -> {
			if(tableListener!= null){
				tableListener.allEventsMenuClicked();
			}
		});

		return goHome;
	}
	
	private MenuItem getMyEvents(){
		MenuItem myEvents = new MenuItem("  My Events ");
		
		myEvents.setOnAction(e ->{
			MyEventsMenuEvent ev = new MyEventsMenuEvent(this, Current.getBusiness());
			if(tableListener!= null){
				tableListener.myEventsMenuClicked(ev);
			}
		});
		return myEvents;
		
	}
	
	private MenuItem getCreateEvent() {
		pane4EventCreation = new Pane4EventCreation();
		MenuController controller = new MenuController(pane4EventCreation);

		MenuItem createEvent = new MenuItem("  Create Event ");
		createEvent.setOnAction(e -> {

			MainWindow.setCenter(pane4EventCreation.getCreatePane(new Text()));
		});
		return createEvent;
	}
	
	
	public void setTableListener(TableListener menu){
		this.tableListener = menu;
	}
}
