package view;

import javafx.scene.control.Label;



import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.CreateButtonEvent;
import controller.Current;
import controller.DeleteButtonEvent;
import controller.EventsListener;
import controller.Pane4EventEvent;
import controller.Pane4EventListener;
import controller.TableController;
import controller.TableTranslator;
import controller.UpdateButtonEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.model4User.model4Establishment.Business;
import model.Event;
import model.EventsBag;
import model.model4Address.Address;
import model.model4User.User;
import model.model4User.model4Customer.Customer;

public class Pane4Events {
	
	private HBox pane1;
	private VBox pane2;
	
	public Pane4Events(){
		
	}
	
	public Pane getPane4AllEvents(){
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
		
		
		filter.getAllEvents(table);
		
		pane2.setSpacing(5);
		pane2.getChildren().addAll(headerPane, table.getTable());
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
		Label header = new Label("MY Events");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		
		
		filter.getMyEvents(table);
		
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
		
		
		filter.getByZip15(table);
		
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
		
		
		filter.getByZip50(table);
		
		pane2.setSpacing(5);
		pane2.getChildren().addAll(headerPane, table.getTable());
		pane1.getChildren().addAll(pane2);
		pane1.setPadding(new Insets(7.5,0,0,0));
		
		return pane1;
	}
}
