package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu {

	private final String house = "\u2302";
	private final String gear = "\u26ed";

	public MainMenu() {

	}

	//Menu Bars
	public MenuBar getCustomerMenuBar(){
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(customerHome(), customerMenu(), customerSettings());
		return menuBar;
	}

	public MenuBar getBusinessMenuBar(){
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(businessHome(), businessMenu(), businessSettings());
		return menuBar;
	}

	//Menus
	private Menu customerHome(){
		Menu home = new Menu("Home " + house);
		home.getItems().addAll(customerGoHome());
		return home;
	}

	private Menu businessHome(){
		Menu home = new Menu("Home " + house);
		home.getItems().addAll(businessGoHome());
		return home;
	}

	private Menu customerMenu(){
		Menu menu = new Menu("Menu");
		menu.getItems().addAll(displayUsers(), displayEvents());
		return menu;
	}

	private Menu businessMenu(){
		Menu menu = new Menu("Menu");
		menu.getItems().addAll(displayUsers(), displayEvents());
		return menu;
	}

	private Menu customerSettings(){
		Menu settings = new Menu("Settings " + gear);
		settings.getItems().addAll(customerEditAccount(), logOut());
		return settings;
	}

	private Menu businessSettings(){
		Menu settings = new Menu("Settings " + gear);
		settings.getItems().addAll(businessEditAccount(), logOut());
		return settings;
	}

	//Menu Items
	private MenuItem customerEditAccount(){
		MenuItem editAccount = new MenuItem("Edit Account");

		editAccount.setOnAction(e -> {
			PaneForCustomer customer = new PaneForCustomer();
			MainWindow.setCenter(customer.getCustomerEditPane());
		});

		return editAccount;
	}

	private MenuItem businessEditAccount(){
		MenuItem editAccount = new MenuItem("Edit Account");

		editAccount.setOnAction(e -> {
			MainWindow.setCenter(null);
		});

		return editAccount;
	}

	private MenuItem logOut(){
		MenuItem logOut = new MenuItem("Log Out");

		logOut.setOnAction(e -> {
			PaneForLogin login = new PaneForLogin();
			PrimaryView.anchor(login.getPane());
		});

		return logOut;
	}

	private MenuItem customerGoHome(){
		MenuItem goHome = new MenuItem("Main Window");

		goHome.setOnAction(e -> {
			MainWindow main = new MainWindow();
			PrimaryView.changePane(main.getCustomerWindow());
		});

		return goHome;
	}

	private MenuItem businessGoHome(){
		MenuItem goHome = new MenuItem("Main Window");

		goHome.setOnAction(e -> {
			MainWindow main = new MainWindow();
			PrimaryView.changePane(main.getBusinessWindow());
		});

		return goHome;
	}

	private MenuItem displayUsers(){
		MenuItem displayUsers = new MenuItem("Display Users");

		displayUsers.setOnAction(e -> {
			PaneDisplayAllUsers users = new PaneDisplayAllUsers();
			MainWindow.setCenter(users.getPane());
		});

		return displayUsers;
	}

	private MenuItem displayEvents(){
		MenuItem displayEvents = new MenuItem("Display Events");

		displayEvents.setOnAction(e -> {
			PaneDisplayAllEvents events = new PaneDisplayAllEvents();
			MainWindow.setCenter(events.getPane());
		});

		return displayEvents;
	}

}
