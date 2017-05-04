package view;


import java.time.LocalDate;

import controller.Current;
import controller.Pane4EventEvent;
import controller.Pane4EventListener;
import controller.Pane4EventsListener;

import controller.TicketButtonEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import model.model4Address.Address;

public class Pane4Event {

	//private PaneForAddress paneForAddress;
	private Pane grid;
	private ImageView image;
	private Button buyTicketBtn;
	private Button buyTablesBtn;
	private Label eventName;
	private Label date;
	private Label location;
	private Label ticketsLeft;
	private Label tablesLeft;
	private VBox ticketPane;
	private FlowPane ticketTablePane;
	
	private Pane4EventListener pane4EventListener;

// NEED to finish implementing gridpane stuff
	
	public Pane4Event() {
		grid = new Pane();
	}

	public GridPane gridPane(){
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.add(eventName, 0, 0);
		gridPane.add(date,0,2);
		gridPane.add(image, 0, 4);
		gridPane.add(ticketPane(), 0, 6);
		return gridPane;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}
	
	public FlowPane ticketTablePane() {
		ticketTablePane = new FlowPane();
		ticketTablePane.setHgap(10);
		ticketTablePane.setVgap(10);
		ticketTablePane.setPadding(new Insets(10,10,10,10));
		ticketTablePane.getChildren().addAll(getTicketsLeft(), getTablesLeft());
		return ticketTablePane;
	}
	
	public Label getTicketsLeft() {
		return ticketsLeft;
	}
	
	public void setTicketsLeft(int tickets) {
		this.ticketsLeft = new Label("Tickets Left: "+ tickets);
	}
	
	public Label getTablesLeft() {
		return tablesLeft;
	}
	
	public void setTablesLeft(int tables) {
		 this.tablesLeft = new Label("Tables Left: "+ tables);
	}
	
	public VBox ticketPane() {
		 ticketPane = new VBox(5);
		 ticketPane.getChildren().addAll(ticketTablePane(), getBuyTicketBtn());
		 return ticketPane;
	}
	
	public ImageView getImage() {
		ImageView image = new ImageView(new Image("http://www.thegarden.com/content/dam/msg/eventImg3/Liberty_201718_328x253.jpg"));
		return image;
	}

	public void setImage(String st) {
		this.image = new ImageView(new Image(st));
	}

	public Button getBuyTicketBtn() {
		buyTicketBtn = new Button("Tickets");
		buyTicketBtn.setOnAction(e -> {
			TicketButtonEvent ev3 = new TicketButtonEvent(this, Current.getEvent());
			Current.setPreviousPane(grid);
			System.out.println(Current.getCustomer().getFirstName());
			if (pane4EventListener != null) {
				pane4EventListener.ticketsClicked(ev3);
			}
		});
		return buyTicketBtn;
	}

	public Label getEventName() {
		return eventName;
	}


	public void setEventName(String st) {
		this.eventName = new Label(st);
		eventName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	}


	public Label getDate() {
		return date;

	}
	
	public void setDate(LocalDate date, Address a) {
		this.date = new Label(date.toString() + " @ "+ a.display());
	}

	public Label descriptionLabel() {
		Label descriptionLabel = new Label();
		return descriptionLabel;
	}

	public Pane getPane(){
		return grid;
	}

	
	public void setPane4EventListener(Pane4EventListener pane) {
		this.pane4EventListener = pane;
	}

}