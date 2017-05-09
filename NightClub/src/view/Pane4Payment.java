package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Current;
import controller.Pane4EventListener;
import controller.PlaceOrderEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Pane4Payment {
	
	private GridPane checkoutPane;
	private TextField streetField;
	private TextField addressField;
	private TextField cityField;
	private TextField zipField;
	private TextField cardNumberField;
	private TextField cardNameField;
	private Label ticketsDisplayLabel;
	private Label tablesDisplayLabel;
	private Label taxDisplayLabel;
	private Label totalDisplayLabel;
	private Label errorLabel = new Label("");
	private ComboBox<String> stateCombo;
	private int ticketCount;
	private int tableCount;
	private Pane4EventListener pane4EventListener;
	
	
	 String[] stateArr = new String[]{
	        "Alabama", "Alaska", "Arizona", "Arkansas", "California",
	        "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
	        "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
	        "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
	        "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
	        "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
	        "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
	        "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
	        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
	        "Wisconsin", "Wyoming"
	    	};
	 List<String> stateList = new ArrayList<String>(Arrays.asList(stateArr));
	
	
	
	public Pane4Payment(){
		checkoutPane = new GridPane();
	}
	
	public HBox checkoutBox() {
		HBox checkoutBox = new HBox();
		checkoutBox.getChildren().addAll(checkoutBox2(), cartPane());
		return checkoutBox;
	}
	
	public GridPane checkoutBox2() {
		
		GridPane checkoutBox2 = new GridPane();
		checkoutBox2.setVgap(20);
		checkoutBox2.setAlignment(Pos.TOP_LEFT);
		checkoutBox2.setPadding(new Insets(10,50,50,30));
		//checkoutBox.add(checkoutLabel(), 0, 0);
		checkoutBox2.setHalignment(addressPane(), HPos.RIGHT);
		checkoutBox2.addColumn(0,checkoutLabel(),billingLabel(),addressPane(),paymentInfoLabel(),paymentPane());
		//checkoutBox.addColumn(1, cartPane());
		return checkoutBox2;
	}
	//--------------------------------- top of VBox ---------------------------------
	public Label checkoutLabel() {
		Label checkoutLabel = new Label("Checkout");
		checkoutLabel.setAlignment(Pos.TOP_RIGHT);
		checkoutLabel.setFont(Font.font("Arial", FontWeight.MEDIUM, 34));
		return checkoutLabel;
	}
	//------------------------------- Cart node and children -------------------------------------------
	public GridPane cartPane() {
		Pane4TablesTickets prevPane = new Pane4TablesTickets();
		GridPane cartPane = new GridPane();
		cartPane.setVgap(15);
		cartPane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		cartPane.setPadding(new Insets(10,10,10,10));
		cartPane.addColumn(0, cartLabel(),ticketsLabel(), tablesLabel(), taxLabel(), totalLabel());
		cartPane.addColumn(1, new Label(),getTicketsDisplayLabel(), getTablesDisplayLabel(),getTaxDisplayLabel(),getTotalDisplayLabel(),modifyBtn());
		return cartPane;
	}
	
	public Label cartLabel() {
		Label cartLabel = new Label("Your Cart:");
		cartLabel.setUnderline(true);
		cartLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,24));
		return cartLabel;
	}
	
	public Label ticketsLabel() {
		Label ticketsLabel = new Label("Tickets:");
		ticketsLabel.setFont(Font.font("Arial", FontWeight.NORMAL,18));
		return ticketsLabel;
	}
	
	public Label tablesLabel() {
		Label tablesLabel = new Label("Tables:");
		tablesLabel.setFont(Font.font("Arial", FontWeight.NORMAL,18));
		return tablesLabel;
	}
	
	public Label taxLabel() {
		Label taxLabel = new Label("Tax:");
		taxLabel.setFont(Font.font("Arial", FontWeight.NORMAL,18));
		return taxLabel;
	}
	
	public Label totalLabel() {
		Label totalLabel = new Label("Total:");
		totalLabel.setFont(Font.font("Arial", FontWeight.NORMAL,18));
		return totalLabel;
	}
	
	public Button modifyBtn() {
		Button modifyBtn = new Button("Modify");
		modifyBtn.setOnAction(e -> {
			MainWindow.setCenter(Current.getPreviousPane());
		});
		
		return modifyBtn;
		
	}
	
	//public void

	// -------------------------------- Billing Address Pane (middle of VBox) ---------------------------------
	public GridPane addressPane() {
		GridPane addressPane = new GridPane();
		addressPane.setVgap(10);
		addressPane.setHgap(10);
		addressPane.setPadding(new Insets(10,10,10,10));

		//Need billing title
		
		addressPane.addColumn(0,addressLabel(),cityLabel(),stateLabel(),zipLabel());
		addressPane.addColumn(1,getAddressField(),getCityField(),getStateCombo(),getZipField());
		
		return addressPane;
	}
	
	//--------------------------------- Payment Info (bottom of VBox) ---------------------------------
	public GridPane paymentPane() {
		GridPane paymentPane = new GridPane();
		paymentPane.setVgap(10);
		paymentPane.setHgap(10);
		
		//paymentPane.addRow(0, paymentInfoLabel());
		paymentPane.addColumn(0, cardNumberLabel(), cardNameLabel(), expirationLabel(), new Label(), placeOrderBtn());
		paymentPane.addColumn(1, getCardNumberField(), getCardNameField(), expirationBox(), getErrorLabel(), cancelBtn());
		//paymentPane.addColumn(2, new Label(), new Label(), yearCombo());
		
		return paymentPane;
	}
	
	// getters & setters --------------------------
	
	public Label billingLabel() {
		Label billingLabel = new Label("Billing Address");
		billingLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,24));
		return billingLabel;
	}
	
	public Label paymentInfoLabel() {
		Label paymentInfoLabel = new Label("Payment Method");
		paymentInfoLabel.setFont(Font.font("Arial", FontWeight.MEDIUM,24));
		return paymentInfoLabel;
	}
	
//	public Label streetNumberLabel() {
//		Label streetNumberLabel = new Label("Street Number ");
//		return streetNumberLabel;
//	}
	
	public Label addressLabel() {
		Label addressLabel = new Label("Address ");
		return addressLabel;
	}
	
	public Label cityLabel() {
		Label cityLabel = new Label("City ");
		return cityLabel;
	}
	
	public Label stateLabel() {
		Label stateLabel = new Label("State ");
		return stateLabel;
	}
	
	public Label zipLabel() {
		Label zipLabel = new Label("Zip Code ");
		return zipLabel;
	}
	
	public Label cardNumberLabel() {
		Label cardNumberLabel = new Label("Card Number");
		return cardNumberLabel;
	}
	
	public Label cardNameLabel() {
		Label cardNameLabel = new Label("Name on Card");
		return cardNameLabel;
	}
	
	public Label expirationLabel() {
		Label expirationLabel = new Label("Expiration");
		return expirationLabel;
	}
	
	
	
//	public TextField getStreetField() {
//		return streetField;
//	}
//
//	public void setStreetField(String street) {
//		this.streetField = new TextField(street);
//	} 

	public Label getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(Label errorLabel) {
		this.errorLabel = errorLabel;
	}

	public TextField getAddressField() {
		return addressField;
	}

	public void setAddressField(String address) {
		this.addressField = new TextField(address);
	}

	public TextField getCityField() {
		return cityField;
	}

	public void setCityField(String city) {
		this.cityField = new TextField(city);
	}
	
	public ComboBox<String> getStateCombo() {
		return stateCombo;
	}
	
	public void setStateCombo(String userState) {
		stateCombo = new ComboBox<>();		
		stateCombo.getItems().addAll(stateList);
		stateCombo.getSelectionModel().select(userState);	
	}
	//------------------------ Expiration Date Combo Boxes ----------------------------
	
	public HBox expirationBox() {
		HBox expirationBox = new HBox(10);
		expirationBox.getChildren().addAll(monthCombo(),yearCombo());
		return expirationBox;
	}
	
	public ComboBox<String> monthCombo() {
		ComboBox<String> monthCombo = new ComboBox<>();
		String[] tempList = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
		List<String> monthList = new ArrayList<String>(Arrays.asList(tempList));
		monthCombo.getItems().addAll(monthList);
		return monthCombo;
	}

	public ComboBox<String> yearCombo() {
		ComboBox<String> yearCombo = new ComboBox<>();
		String[] tempList = new String[]{"2017","2018","2019","2020","2021"};
		List<String> yearList = new ArrayList<String>(Arrays.asList(tempList));
		yearCombo.getItems().addAll(yearList);
		return yearCombo;
	}
	// ---------------------- Place order and Cancel Buttons -------------------------
	public Button placeOrderBtn() {
		Button placeOrderBtn = new Button("Place Order");
		placeOrderBtn.setOnAction(e -> {
			PlaceOrderEvent ev = new PlaceOrderEvent(this, Current.getEvent(), Current.getCustomer(),Current.getEvent().getBusiness(),getTicketAmount(),getTableAmount());
			if (pane4EventListener != null) {
				pane4EventListener.placeOrderClicked(ev);
			}
		});
		
		return placeOrderBtn;
	}
	
	public Button cancelBtn() {
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setOnAction(e-> {
			MainWindow.setCenter(Current.getPreviousPane());
		});
		return cancelBtn;
	}
	
	public TextField getZipField() {
		return zipField;
	}

	public void setZipField(String zip) {
		this.zipField = new TextField(zip);
	}

	public TextField getCardNumberField() {
		return cardNumberField;
	}

	public void setCardNumberField(String cardNumber) {
		this.cardNumberField = new TextField(cardNumber);
	}

	public TextField getCardNameField() {
		return cardNameField;
	}

	public void setCardNameField(String cardName) {
		this.cardNameField = new TextField(cardName);
	}
	
	public Label getTicketsDisplayLabel() {
		return ticketsDisplayLabel;
	}

	public void setTicketsDisplayLabel(String ticketsDisplayLabel) {
		this.ticketsDisplayLabel = new Label(ticketsDisplayLabel);
		this.ticketsDisplayLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD,16));
	}

	public Label getTablesDisplayLabel() {
		return tablesDisplayLabel;
	}

	public void setTablesDisplayLabel(String tablesDisplayLabel) {
		this.tablesDisplayLabel = new Label(tablesDisplayLabel);
		this.tablesDisplayLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD,16)); 
	}

	public Label getTaxDisplayLabel() {
		return taxDisplayLabel;
	}

	public void setTaxDisplayLabel(String taxDisplayLabel) {
		this.taxDisplayLabel = new Label(taxDisplayLabel);
		this.taxDisplayLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD,16)); 
	}

	public Label getTotalDisplayLabel() {
		return totalDisplayLabel;
	}

	public void setTotalDisplayLabel(String totalDisplayLabel) {
		this.totalDisplayLabel = new Label(totalDisplayLabel);
		this.totalDisplayLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD,16)); 
	}

	public GridPane getPane(){
		return checkoutPane;
	}
	
	
	public void setPane4EventListener(Pane4EventListener pane4EventListener) {
		this.pane4EventListener = pane4EventListener;
	}

	public void setTicketCount(int ticketAmount) {
		this.ticketCount = ticketAmount;	
	}
	
	public void setTableCount(int tableAmount) {
		this.tableCount = tableAmount;
	}
	
	public int getTicketAmount() {
		return this.ticketCount;
	}
	
	public int getTableAmount() {
		return this.tableCount;
	}
	
}
