package view;

import java.util.List;

import controller.CustomerAccountController;
import controller.EventsListener;
import controller.ExpandEventController;
import controller.MenuController;
import controller.TableController;
import controller.TableTranslator;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainWindow {
	
	private static BorderPane mainWindow = new BorderPane();

	public MainWindow() {
	}

	public static BorderPane getCustomerWindow() {
		Pane4Events eventPane = new Pane4Events();
		MainMenu menuBar = new MainMenu();
		MenuController controller = new MenuController(menuBar);
		mainWindow.setRight(null);
		mainWindow.setTop(menuBar.getCustomerMenuBar());
		return mainWindow;
	}
	
	public static BorderPane getBusinessWindow() {
		Pane4Events events = new Pane4Events();
		MainMenu menuBar = new MainMenu();
		MenuController controller = new MenuController(menuBar);
		mainWindow.setRight(null);
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
	

	public static void setBottom(Node node) {
		mainWindow.setBottom(null);
		mainWindow.setBottom(node);
	}
	
	public static void setRight(Node node) {
		mainWindow.setRight(null);
		mainWindow.setRight(node);
	}

	
	public Pane getPane(){
		return mainWindow;
	}
	

}