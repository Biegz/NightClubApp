package controller;

import java.util.ArrayList;

import controller.tableEvents.MyEventsMenuEvent;
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
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;
import view.MainMenu;
import view.MainWindow;
import view.Pane4Event;
import view.Pane4Table;
import view.PaneForLogin;

public class MenuController {
	
	private MainMenu view;
	private Pane4Table view2;
	private PaneForLogin view3;
	
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
			
			
		});
		
		
	}	
	
	public MenuController(PaneForLogin view3){
		this.view3 = view3;
		this.view2 = new Pane4Table();
		translator = new TableTranslator();
		tableController = new TableController(view2);
		
		view3.setTableListener(new TableListener(){
			public void allEventsLogin(){
					table = view2.getTable(translator.getAllEvents());
					displayAllEvents(table);
			
			}
		});
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
	
	
}