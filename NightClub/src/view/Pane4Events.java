package view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import controller.TableController;
import controller.TableTranslator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Pane4Events {
	
	private HBox pane1;
	private VBox pane2;
	
	public Pane4Events(){
		
	}
	
	public Pane getPane4AllEvents(Node temp){
		pane1 = new HBox();
		pane2 = new VBox();
		VBox headerPane = new VBox();
		
		Pane4Table table = new Pane4Table();
		TableTranslator filter = new TableTranslator();
		TableController controller = new TableController(table);
		
		Label header = new Label("All Events");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		
		
		pane2.setSpacing(5);
		pane2.getChildren().addAll(headerPane, table.getTable(temp));
		pane1.getChildren().addAll(pane2);
		pane1.setPadding(new Insets(7.5,0,0,0));
		
		return pane1;
	}
	
	public Pane getPane4MyEvents(){
		pane1 = new HBox();
		pane2 = new VBox();
		VBox headerPane = new VBox();
		Pane4EditEvents delete = new Pane4EditEvents();
		
		Pane4Table table = new Pane4Table();
		TableTranslator filter = new TableTranslator();
		TableController controller = new TableController(table);
		Label header = new Label("My Events");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		
		//filter.getMyEvents(table);
		
		pane2.setSpacing(5);
		pane2.getChildren().addAll(headerPane, table.getTable(), delete.getDeleteButton());
		pane1.getChildren().addAll(pane2);
		pane1.setPadding(new Insets(7.5,0,0,0));
		
		return pane1;
	}
	
	public Pane getZip15(){
		pane1 = new HBox();
		pane2 = new VBox();
		VBox headerPane = new VBox();
		
		Pane4Table table = new Pane4Table();
		TableTranslator filter = new TableTranslator();
		TableController controller = new TableController(table);
		Label header = new Label("All Events within 15 Miles*");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		
		//filter.getByZip15(table);
		
		pane2.setSpacing(5);
		pane2.getChildren().addAll(headerPane, table.getTable());
		pane1.getChildren().addAll(pane2);
		pane1.setPadding(new Insets(7.5,0,0,0));
		
		return pane1;
	}
	
	public Pane getZip50(){
		pane1 = new HBox();
		pane2 = new VBox();
		VBox headerPane = new VBox();
		
		Pane4Table table = new Pane4Table();
		TableTranslator filter = new TableTranslator();
		TableController controller = new TableController(table);
		Label header = new Label("All Events within 50 Miles*");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		
		//filter.getByZip50(table);
		
		pane2.setSpacing(5);
		pane2.getChildren().addAll(headerPane, table.getTable());
		pane1.getChildren().addAll(pane2);
		pane1.setPadding(new Insets(7.5,0,0,0));
		
		return pane1;
	}
}
