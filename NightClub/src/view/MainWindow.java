package view;

import controller.EventController;
import controller.ExpandEventController;
import controller.TableController;
import controller.TableTranslator;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainWindow {
	
	private static BorderPane mainWindow;
	

	public MainWindow() {
		mainWindow = new BorderPane();
	}

	public BorderPane getCustomerWindow() {
		Pane4Events eventPane = new Pane4Events();
		MainMenu menuBar = new MainMenu();
		mainWindow.setLeft(eventPane.getPane4AllEvents());
		mainWindow.setTop(menuBar.getCustomerMenuBar());
		return mainWindow;
	}
	
	public BorderPane getBusinessWindow() {
		Pane4Events events = new Pane4Events();
		MainMenu menuBar = new MainMenu();
		mainWindow.setLeft(events.getPane4MyEvents());
		mainWindow.setTop(menuBar.getBusinessMenuBar());
		return mainWindow;
	}
	
	public static void setCenter(Node node){
		mainWindow.setCenter(null);
		mainWindow.setCenter(node);
	}
	
	public static void setLeft(Node node){
		mainWindow.setLeft(null);
		mainWindow.setLeft(node);
	}
	
	
	public Pane getPane(){
		return mainWindow;
	}
	


}