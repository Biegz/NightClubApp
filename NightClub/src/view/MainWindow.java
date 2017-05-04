package view;

import controller.ExpandEventController;
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
		Pane4Events pane4Events = new Pane4Events();
		ExpandEventController controller = new ExpandEventController(pane4Events);
		MainMenu menuBar = new MainMenu();
		mainWindow.setLeft(pane4Events.getCustomerEventPane());
		mainWindow.setTop(menuBar.getCustomerMenuBar());
		return mainWindow;
	}
	
	public BorderPane getBusinessWindow() {
		MainMenu menuBar = new MainMenu();
		EstablishmentHLPane establishmentHLPane = new EstablishmentHLPane();
		mainWindow.setLeft(establishmentHLPane.getHyperlinkPane());
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