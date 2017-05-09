package view;

import java.text.DecimalFormat;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Pane4Receipt {
	private Label receiptLabel;
	private Label ticketLabel;
	private Label tableLabel;
	private Label taxLabel;
	private Label totalLabel;
	private Label cardLabel;
	private Label dateLabel;
	private Label eventLabel;
	DecimalFormat df = new DecimalFormat("#.##");

	
	private Pane receiptPane;
	
	public Pane4Receipt() {
		receiptPane = new Pane();
	}
	
	public GridPane receiptGrid() {
		GridPane receiptGrid = new GridPane(); 
		receiptGrid.setVgap(10);
		receiptGrid.setPadding(new Insets(15,10,10,15));
		receiptGrid.addColumn(0, getReceiptLabel(),new Label(), orderLabel(), getEventLabel(), getDateLabel(), orderGrid(), new Label(), myOrdersBtn());
		return receiptGrid;
	}
	
	public GridPane orderGrid() {
		GridPane orderGrid = new GridPane();
		orderGrid.setPadding(new Insets(0,0,0,15));
		orderGrid.setVgap(15);
		orderGrid.setHgap(10);
		orderGrid.addColumn(0, ticketHeaderLabel(),tableHeaderLabel(),taxHeaderLabel(),totalHeaderLabel());
		orderGrid.addColumn(1, getTicketLabel(), getTableLabel(), getTaxLabel(), getTotalLabel());
		return orderGrid;
	}
	
	// ----------------------------- Buttons ------------------------------------

	public Button myOrdersBtn() {
		Button myOrdersBtn = new Button("My Orders");
		myOrdersBtn.setOnAction(e -> {
			
		});
		return myOrdersBtn;
	}

	//------------------------------ Static Labels ----------------------------------
	
	public Label orderLabel() {
		Label orderLabel = new Label("Order Summary:");
		orderLabel.setPadding(new Insets(10,10,10,0));
		orderLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));
		orderLabel.setUnderline(true);
		return orderLabel;
	}
	
	public Label ticketHeaderLabel() {
		Label ticketHeaderLabel = new Label("Tickets");
		ticketHeaderLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,16));
		return ticketHeaderLabel;
	}
	
	public Label tableHeaderLabel() {
		Label tableHeaderLabel = new Label("Tables");
		tableHeaderLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,16));
		return tableHeaderLabel;
	}
	
	public Label taxHeaderLabel() {
		Label taxHeaderLabel = new Label("Tax:");
		taxHeaderLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,16));
		return taxHeaderLabel;
	}
	
	public Label totalHeaderLabel() {
		Label totalHeaderLabel = new Label("Total:");
		totalHeaderLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,16));
		return totalHeaderLabel;
	}
	
	
	//------------------------------getters & setters -------------------------------
	
	public Label getReceiptLabel() {
		return receiptLabel;
	}

	public void setReceiptLabel(String customer) {
		this.receiptLabel = new Label("Thanks for your order, "+ customer);
		receiptLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD,28));
	}

	public Label getTicketLabel() {
		return ticketLabel;
	}

	public void setTicketLabel(int tickets, double cost) {
		this.ticketLabel = new Label(tickets + " x $" + df.format(cost));
		ticketLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,14));
	}

	public Label getTableLabel() {
		return tableLabel;
	}

	public void setTableLabel(int tables, double cost) {
		this.tableLabel = new Label(tables + " x $" + df.format(cost));
		tableLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,14));
	}

	public Label getTaxLabel() {
		return taxLabel;
	}

	public void setTaxLabel(double tax) {
		this.taxLabel = new Label ("$" + df.format(tax));
		taxLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,14));
	}

	public Label getTotalLabel() {
		return totalLabel;
	}

	public void setTotalLabel(double total) {
		this.totalLabel = new Label("$" + df.format(total));
		totalLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,14));
	}

	public Label getCardLabel() {
		return cardLabel;
	}

	public void setCardLabel(Label cardLabel) {
		this.cardLabel = cardLabel;
	}

	public Label getDateLabel() {
		return dateLabel;
	}

	public void setDateLabel(LocalDate date) {
		this.dateLabel = new Label("Ordered on: " + date.getMonth() +" "+ date.getDayOfMonth()+ ", "+ date.getYear());
		dateLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,16));
		dateLabel.setPadding(new Insets(0,0,15,0));
	}

	public Label getEventLabel() {
		return eventLabel;
	}

	public void setEventLabel(String eventName) {
		this.eventLabel = new Label("Order for: " +eventName);
		eventLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,16));

	}

	
	
}