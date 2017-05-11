package view;

import controller.TableTranslator;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MainMenu {

	private final String house = "\u2302";
	private final String gear = "\u26ed";

	public MainMenu() {

	}

	// Menu Bars
	public MenuBar getCustomerMenuBar() {
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(customerHome(), customerMenu(), getCustomerEvents(), customerSettings());
		return menuBar;
	}

	public MenuBar getBusinessMenuBar() {
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(businessHome(), businessMenu(), getBusinessEvents(), businessSettings());
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
		settings.getItems().addAll(customerEditAccount(), logOut());
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
		events.getItems().addAll(getAllEvents(), getNearMe());
		return events;
	}
	
	
	
	//-------------------------Menu Items For Both------------------------------------

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

		Pane4Events events = new Pane4Events();
		
		MenuItem allEvents = new MenuItem("  All Events ");
		
		allEvents.setOnAction(e ->{
			MainWindow.setLeft(events.getPane4AllEvents());
		});
		return allEvents;
		
	}
	
	
	
	// ------------------------Menu Items For Customer-----------------------------------
	private MenuItem customerEditAccount() {
		MenuItem editAccount = new MenuItem("  Edit Account");

		editAccount.setOnAction(e -> {
			PaneForCustomer customer = new PaneForCustomer();
			MainWindow.setCenter(customer.getUpdatePane());
		});

		return editAccount;
	}

	private MenuItem customerGoHome() {
		MenuItem goHome = new MenuItem("  Main Window ");

		goHome.setOnAction(e -> {
			MainWindow main = new MainWindow();
			PrimaryView.changePane(main.getCustomerWindow());
		});

		return goHome;
	}

	private Menu getNearMe(){
		
		Menu nearMe = new Menu("  Near Me ");
		nearMe.getItems().addAll(getWithin15Miles(), getWithin50Miles());
		return nearMe;
	}
	
	private MenuItem getWithin15Miles(){
		Pane4Events events = new Pane4Events();
		
		MenuItem within15 = new MenuItem("  Within 10 Miles ");
		within15.setOnAction(e ->{
			MainWindow.setLeft(events.getZip15());
		});
		
		return within15;
	}
	
	private MenuItem getWithin50Miles(){
		Pane4Events events = new Pane4Events();
		
		MenuItem within50 = new MenuItem("  Within 50 Miles ");
		within50.setOnAction(e ->{
			MainWindow.setLeft(events.getZip50());
		});
		
		return within50;
	}
	
	
	
	//-----------------------------Menu Items for Business-----------------------

	private MenuItem businessEditAccount() {
		MenuItem editAccount = new MenuItem("  My Account ");

		editAccount.setOnAction(e -> {
			EstablishmentHLPane pane = new EstablishmentHLPane();
			MainWindow.setCenter(pane.getHyperlinkPane());
		});

		return editAccount;
	}
	
	private MenuItem businessGoHome() {
		MenuItem goHome = new MenuItem("  Main Window ");

		goHome.setOnAction(e -> {
			MainWindow main = new MainWindow();
			PrimaryView.changePane(main.getBusinessWindow());
		});

		return goHome;
	}
	
	private MenuItem getMyEvents(){
		Pane4Events events = new Pane4Events();
		MenuItem myEvents = new MenuItem("  My Events ");
		
		myEvents.setOnAction(e ->{
			MainWindow.setLeft(events.getPane4MyEvents());
		});
		return myEvents;
		
	}
	
	private MenuItem getCreateEvent() {
		Pane4EventCreation pane4EventCreation = new Pane4EventCreation();
		MenuItem createEvent = new MenuItem("  Create Event ");
		createEvent.setOnAction(e -> {
			MainWindow.setCenter(pane4EventCreation.getCreatePane());
		});
		return createEvent;
	}
}
