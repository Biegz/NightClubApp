package view;

import controller.EventController;
import controller.ExpandEventController;
import controller.TableController;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainWindow {
	
	private static BorderPane mainWindow;
	

	public MainWindow() {
		mainWindow = new BorderPane();
	}

	public BorderPane getCustomerWindow() {
		Pane4Table table = new Pane4Table();
		TableController controller = new TableController(table);
		System.out.println("Just Created the controller object in view!");
		MainMenu menuBar = new MainMenu();
		mainWindow.setLeft(table.getCustomerEventPane());
		mainWindow.setTop(menuBar.getCustomerMenuBar());
		return mainWindow;
	}
	
	public BorderPane getBusinessWindow() {
		Pane4Table table = new Pane4Table();
		TableController controller = new TableController(table);
		MainMenu menuBar = new MainMenu();
		EstablishmentHLPane establishmentHLPane = new EstablishmentHLPane();
		mainWindow.setLeft(table.getCustomerEventPane());
		mainWindow.setTop(menuBar.getBusinessMenuBar());
		return mainWindow;
	}
	
	public static void setCenter(Node node){
		mainWindow.setCenter(null);
		mainWindow.setCenter(node);
	}
	
	
	public Pane getPane(){
		return mainWindow;
	}
	


}