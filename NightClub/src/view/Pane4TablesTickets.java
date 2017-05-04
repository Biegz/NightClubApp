package view;

import java.text.DecimalFormat;

import controller.CheckoutButtonEvent;
import controller.Current;
import controller.Pane4EventListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Pane4TablesTickets {

	private GridPane pane;
	private Label ticketPriceLabel;
	private Label tablePriceLabel;
	private ComboBox tableCombo;
	private ComboBox ticketCombo;
	private HBox tableBox;
	private HBox ticketBox;
	private Button purchaseBtn;
	private Label ticketCostLabel;
	private Label tableCostLabel;
	public double ticketPrice;
	public double tablePrice;
	public double ticketCost = 0;
	public double tableCost = 0;
	public int ticketAmount = 0;
	public int tableAmount = 0;
	DecimalFormat df = new DecimalFormat("#.##");
	
	private Pane4EventListener pane4EventListener;
	
	
	public Pane4TablesTickets(){
		pane = new GridPane();
	}
	
	public GridPane buyBox(){
		GridPane buyBox = new GridPane();
		//buyBox.setGridLinesVisible(true);
		buyBox.setVgap(15);
		buyBox.setHgap(10);
		buyBox.setPrefWidth(30);
		buyBox.setPadding(new Insets(10,10,10,10));
				
		buyBox.addColumn(0, getTicketLabel(), getTableLabel(), purchaseBtn());
		buyBox.addColumn(1, getTicketPriceLabel(), getTablePriceLabel(), cancelBtn());
		buyBox.addColumn(2, getTicketCombo(), getTableCombo(), new Label(""));
		buyBox.addColumn(3, getTicketCostLabel(), getTableCostLabel(), getTotalTaxLabel(), getTotalCostLabel());
		return buyBox;
	}
	
	public GridPane refreshBox(Node n1, Node n2){
		GridPane buyBox = new GridPane();
		buyBox.setVgap(15);
		buyBox.setHgap(10);
		buyBox.setPadding(new Insets(10,10,10,10));
		
		buyBox.addColumn(0, getTicketLabel(), getTableLabel(), purchaseBtn());
		buyBox.addColumn(1, getTicketPriceLabel(), getTablePriceLabel(), cancelBtn());
		buyBox.addColumn(2, getTicketCombo(), getTableCombo(), new Label(""));
		buyBox.addColumn(3, n1, n2, getTotalTaxLabel(), getTotalCostLabel());
		return buyBox;
	}
	
	public Label getTicketLabel() {
		Label ticketLabel = new Label("Tickets: ");
		ticketLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
		return ticketLabel; 
	}
	
	public Label getTableLabel() {
		Label tableLabel = new Label("Tables:  ");
		tableLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 16));
		return tableLabel;
	}
	
	public void setTicketPrice(double price) {
		this.ticketPrice = price;	
	}
	
	public Label getTicketPriceLabel() {
		Label ticketPriceLabel = new Label("$" +ticketPrice+ " ea");
		ticketPriceLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 12));
		return ticketPriceLabel;
	}
	
	public void setTablePrice(double price){
		this.tablePrice = price;
	}
	
	public Label getTablePriceLabel() {
		Label tablePriceLabel = new Label("$" +tablePrice+ " ea");
		tablePriceLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 12));
		return tablePriceLabel;
	}
	
	public void setTicketCostLabel(double cost) {
		this.ticketCostLabel = new Label("$ "+df.format(cost));
		ticketCostLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));

	}
	
	public Label getTicketCostLabel() {
		return this.ticketCostLabel;
	}
	
	public void setTableCostLabel(double cost) {
		tableCostLabel = new Label("$ "+df.format(cost));
		tableCostLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
	}
	
	public Label getTableCostLabel() {
		return this.tableCostLabel;
	}
	
	public Label getTotalCostLabel() {
		Label totalCostLabel = new Label("Total: $"+(df.format((ticketCost+tableCost)+((ticketCost+tableCost)*.045))));
		return totalCostLabel;
	}
	
	public Label getTotalTaxLabel() {
		Label totalTaxLabel = new Label("Tax: $" + (df.format((ticketCost+tableCost)*.045))+" (% 4.5)");
		return totalTaxLabel;
	}
	
	public void setTableCombo(int n) {
		this.tableCombo = new ComboBox();
		String[] temparr = new String[n];
		for (int i = 0; i < n; i++) {
			temparr[i] = Integer.toString(i);
		}
		tableCombo.getItems().addAll(temparr);
		tableCombo.getSelectionModel().select(0);
	}
	
	public void setTicketCombo(int n) {
		this.ticketCombo = new ComboBox();
		String[] temparr = new String[n];
		for (int i = 0; i < n; i++) {
			temparr[i] = Integer.toString(i);
		}
		ticketCombo.getItems().addAll(temparr);
		ticketCombo.getSelectionModel().select(0);
	}
	
	public ComboBox getTicketCombo() {
		this.ticketCombo.setOnAction(e-> {
			String tempString = (String) ticketCombo.getSelectionModel().getSelectedItem();
			int temp = Integer.parseInt(tempString);
			ticketAmount = temp;
			this.ticketCost = temp*ticketPrice;
			this.getPane().getChildren().remove(getTicketCostLabel());
			this.getPane().add(new Label("$ "+ this.ticketCost),1,1);
			MainWindow.setCenter(this.refreshBox(new Label("$ "+ df.format(ticketCost)), new Label("$ "+ df.format(tableCost))));
		});
		return this.ticketCombo;
	}
	
	public ComboBox getTableCombo() {
		this.tableCombo.setOnAction(e-> {
			String tempString = (String) (tableCombo.getSelectionModel().getSelectedItem());
			int temp = Integer.parseInt(tempString);
			tableAmount = temp;
			this.tableCost = temp*tablePrice;
			this.getPane().getChildren().remove(getTableCostLabel());
			this.getPane().add(new Label("$ "+ this.tableCost),1,1);
			MainWindow.setCenter(this.refreshBox(new Label("$ "+ df.format(ticketCost)), new Label("$ "+ df.format(tableCost))));
			tableCombo.setDisable(false);
		});
		return this.tableCombo;
	}
	
	public Button purchaseBtn() {
		Button purchaseBtn = new Button("Checkout");
		purchaseBtn.setOnAction(e-> {
			CheckoutButtonEvent ev = new CheckoutButtonEvent(this, Current.getEvent(), Current.getCustomer(), this.ticketAmount, this.tableAmount);
			Current.setPreviousPane(pane);
			if (pane4EventListener != null) {
				pane4EventListener.checkoutClicked(ev);
			}
		});
		return purchaseBtn;
	}
	
	public Button cancelBtn() {
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setOnAction(e-> {
			ticketCost = 0;
			tableCost = 0;
			tablePrice = 0;
			ticketPrice = 0;
			MainWindow.setCenter(Current.getPreviousPane());
		});
		return cancelBtn;
	}
	
	public GridPane getPane(){
		return pane;
	}
	
	public void setPane4EventListener(Pane4EventListener pane) {
		this.pane4EventListener = pane;
	}
}