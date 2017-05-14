package controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import javafx.scene.text.Text;
import model.Event;
import model.Table;
import model.Ticket;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;
import view.CustomerHLPane;
import view.MainWindow;
import view.Pane4Event;
import view.Pane4MyItems;
import view.Pane4Payment;
import view.Pane4Receipt;
import view.Pane4Table;
import view.Pane4TablesTickets;
//import view.Pane4TicketsView;

public class ExpandEventController implements Observer{

	private Pane4Table view;
	private Event modelEvent;
	private Customer modelCustomer;
	private Business modelBusiness;
	private Pane4Event view3;
	private Pane4TablesTickets view4;
	private Pane4Payment view5;
	private Pane4Receipt view6;
	private Pane4Table view7;
	//private Pane4TicketsView view7;
	private Pane4MyItems view8;
	private CustomerHLPane view9;

	DecimalFormat df = new DecimalFormat("#.##");
	 
	public ExpandEventController(Pane4Event view3) {
		this.view3 = view3;
		this.view4 = new Pane4TablesTickets();
		this.view5 = new Pane4Payment();
		this.view6 = new Pane4Receipt();
		//this.view7 = new Pane4TicketsView();
		this.view8 = new Pane4MyItems();
		this.view9 = new CustomerHLPane();

		
		view3.setPane4EventListener(new Pane4EventListener() {
			public void ticketsClicked(TicketButtonEvent ev) {
				modelEvent = ev.getEvent();
				
				Current.setPreviousPane(view3.gridPane(view3.getBuyTicketBtn()));
				int tempTickets = modelEvent.getTicketsAvailable();
				int tempTables = modelEvent.getTablesAvailable();
				
				if (tempTickets < 10) {
					view4.setTicketCombo(tempTickets);
				} else {
					view4.setTicketCombo(10);
				}
				
				if (tempTables < 10) {
					view4.setTableCombo(tempTables);
				} else {
					view4.setTableCombo(10);
				}
				
				view4.setTablePrice(modelEvent.getTablePrice());
				view4.setTicketPrice(modelEvent.getTicketPrice());

				view4.setTicketCostLabel(0.00);
				view4.setTableCostLabel(0.00);
				
				displayTickets();
			}
			
		});
		
		view4.setPane4EventListener(new Pane4EventListener() {
			
			public void checkoutClicked(CheckoutButtonEvent ev) {
				modelEvent = ev.getEvent();
				modelCustomer = ev.getCustomer();
				Current.setPreviousPane(view4.buyBox());
				int ticketAmount = ev.getTicketAmount();
				int tableAmount = ev.getTableAmount();
//				if (ev.getTableAmount() == 0 && ev.getTicketAmount() == 0) {
//					
//				}
				double taxAmount = ((ticketAmount*modelEvent.getTicketPrice()) + (tableAmount*modelEvent.getTablePrice()))*(0.045);
				double totalAmount = taxAmount+ (ticketAmount*modelEvent.getTicketPrice()) + (tableAmount*modelEvent.getTablePrice());
				view5.setTicketCount(ticketAmount);
				view5.setTableCount(tableAmount);
//				view5.setStreetField(modelCustomer.getAddress().getNumber());
				view5.setAddressField(modelCustomer.getAddress().getStreet());
				view5.setCityField(modelCustomer.getAddress().getCity());
				view5.setStateCombo(modelCustomer.getAddress().getState());
				view5.setZipField(modelCustomer.getAddress().getZipcode());
				view5.setCardNameField(modelCustomer.getFirstName()+" "+modelCustomer.getLastName());
				view5.setCardNumberField("");
				view5.setTicketsDisplayLabel((ticketAmount)+" x $"+(df.format(modelEvent.getTicketPrice())));
				view5.setTablesDisplayLabel((tableAmount)+" x $"+(df.format(modelEvent.getTablePrice())));
				view5.setTaxDisplayLabel("$"+(df.format(taxAmount)));
				view5.setTotalDisplayLabel("$"+df.format(totalAmount));

				displayCheckout();
			}
		});
		
		view5.setPane4EventListener(new Pane4EventListener() {
			
			public void placeOrderClicked(PlaceOrderEvent ev) {
				modelEvent = ev.getEvent();
				modelCustomer = ev.getCustomer();
				modelBusiness = ev.getBusiness();
				if (modelCustomer.findEvent(modelEvent.getEventName()) != true) {
					modelCustomer.setEventList(modelEvent);
				} else {
				//modelCustomer.setEventList(modelEvent);
				int ticketAmount = ev.getTicketCount();
				int tableAmount = ev.getTableCount();
				double taxAmount = ((ticketAmount*modelEvent.getTicketPrice()) + (tableAmount*modelEvent.getTablePrice()))*(0.045);
				double totalAmount = taxAmount+ (ticketAmount*modelEvent.getTicketPrice()) + (tableAmount*modelEvent.getTablePrice());

				CustomerTicketProcessing ticketProcessor = new CustomerTicketProcessing(modelEvent);
				ticketProcessor.buyTicket(ticketAmount, modelCustomer, modelEvent, modelBusiness);
				ticketProcessor.buyTable(tableAmount, modelCustomer, modelEvent, modelBusiness);
				
				view6.setEventLabel(modelEvent.getEventName());
				view6.setReceiptLabel(modelCustomer.getFirstName());
				view6.setDateLabel(LocalDate.now());
				view6.setTicketLabel(ev.getTicketCount(), modelEvent.getTicketPrice());
				view6.setTableLabel(ev.getTableCount(), modelEvent.getTablePrice());
				view6.setTaxLabel(taxAmount);
				view6.setTotalLabel(totalAmount);
				displayReceipt();
				}
			}
		});
		
		view6.setPane4EventListener(new Pane4EventListener() {
			
			public void myOrdersClicked(MyOrderEvent ev) {
				modelCustomer = ev.getCustomer();
				CustomerAccountController hlController = new CustomerAccountController(view9);
				for (Event e: modelCustomer.getEventList()) {
					System.out.println(e.getEventName());
				}
				//view7.setMyEventsTable(modelCustomer.getEventList());
				displayMyAccount();
			}
			
		});
		
//		view7.setPane4EventListener(new Pane4EventListener() {
//			
//			public void eventRowSelected(ClickEventEvent ev) {
//				modelCustomer = ev.getCustomer();
//				modelEvent = ev.getEvent();
//				int ticketCount = 0;
//				int tableCount = 0;
//				for(int i = 0; i < modelCustomer.getTicketList().size(); i++) {
//					if(modelCustomer.getTicketList().get(i).getEvent().getEventName().equalsIgnoreCase(modelEvent.getEventName())) {
//						ticketCount++;
//					}
//				}
//				for(int i = 0; i < modelCustomer.getTableList().size(); i++) {
//					if(modelCustomer.getTableList().get(i).getEvent().getEventName().equalsIgnoreCase(modelEvent.getEventName())){
//						tableCount++;
//					}
//				}
//				
//				view8.setTicketLabel(ticketCount, modelEvent.getTicketPrice(), modelEvent.getTicketPrice()*ticketCount);
//				view8.setTableLabel(tableCount, modelEvent.getTablePrice(), modelEvent.getTablePrice()*tableCount);
//				view8.setEventLabel(modelEvent.getEventName(), modelEvent.getDate());
//				
//				displayMyItems();
//			
//			}
//		});

	}
		public ExpandEventController(Pane4MyItems view8) {
			this.view = new Pane4Table();
			this.view8 = view8;
			ExpandEventController event = new ExpandEventController(view);
			
		view8.setPane4EventListener(new Pane4EventListener() {
			
			public void returnEventClicked(ReturnTicketEvent ev) {
				System.out.println("Current Customer: "+Current.getCustomer());
				System.out.println("In ticket control");
				System.out.println("In ticket control");
				System.out.println("In ticket control");
				modelCustomer = ev.getCustomer();
				modelEvent = ev.getEvent();
				CustomerTicketProcessing ticketProcessor = new CustomerTicketProcessing(modelEvent);				
				// --------- Removing Selected Event tickets from Customer ticketList
				ArrayList<Ticket> tempList = modelCustomer.getTicketList();
				for (Iterator<Ticket> iterator = tempList.iterator(); iterator.hasNext();) {
					Ticket tempTicket = iterator.next();
					if(tempTicket.getEvent().getEventName().equalsIgnoreCase(modelEvent.getEventName())){
					//if(!tempTicket.getDate().isBefore(LocalDate.now())){
						ticketProcessor.returnTicket(tempTicket, modelCustomer);
						iterator.remove();
						System.out.println("Returned " +tempTicket.getCost());
					}
				}
				
				// --------- Removing Selected Event tables from Customer tableList
				
				ArrayList<Table> tempTables = modelCustomer.getTableList();
				for (Iterator<Table> iterator = tempTables.iterator(); iterator.hasNext();) {
					Table tempTable = iterator.next();
					if(tempTable.getEvent().getEventName().equalsIgnoreCase(modelEvent.getEventName())){
					//if(!tempTicket.getDate().isBefore(LocalDate.now())){
						ticketProcessor.returnTable(tempTable, modelCustomer);
						iterator.remove();
						System.out.println("Returned " +tempTable.getCost());
					}
				}
				

				// --------- Removing Selected Event from Customer eventList
				for(Iterator<Event> iter = modelCustomer.getEventList().iterator(); iter.hasNext();) {
					Event tempEvent = iter.next();
					if(tempEvent.getEventName().equalsIgnoreCase(modelEvent.getEventName())) {
						if(modelCustomer.findTable(modelEvent) == null) {
							iter.remove();
						
						} 
					}
				}
				
				System.out.println("___________");
				for(Event e : modelCustomer.getEventList()){
					System.out.println(e.getEventName());
				}
				
				TableTranslator translator = new TableTranslator();
				view.setMyEventsTable(translator.getMyUpcomingEvents(modelCustomer));
				displayMyTickets();
				IO.saveAll();
			}
		});
		
	}
	
	private void addOberserver(){
		modelEvent.addObserver(this);
	}

	public ExpandEventController(Pane4Table view){
		this.view = view;
		view8 = new Pane4MyItems();
		//ExpandEventController control = new ExpandEventController(view8);

		view.setPane4EventListener(new Pane4EventListener() {
			
			public void eventRowSelected(ClickEventEvent ev) {
				modelCustomer = ev.getCustomer();
				modelEvent = ev.getEvent();
				Current.setEvent(modelEvent);
				
				int ticketCount = 0;
				int tableCount = 0;
				for(int i = 0; i < modelCustomer.getTicketList().size(); i++) {
					if(modelCustomer.getTicketList().get(i).getEvent().getEventName().equalsIgnoreCase(modelEvent.getEventName())) {
						ticketCount++;
					}
				}
				for(int i = 0; i < modelCustomer.getTableList().size(); i++) {
					if(modelCustomer.getTableList().get(i).getEvent().getEventName().equalsIgnoreCase(modelEvent.getEventName())){
						tableCount++;
					}
				}
				
				view8.setTicketLabel(ticketCount, modelEvent.getTicketPrice(), modelEvent.getTicketPrice()*ticketCount);
				view8.setTableLabel(tableCount, modelEvent.getTablePrice(), modelEvent.getTablePrice()*tableCount);
				view8.setEventLabel(modelEvent.getEventName(), modelEvent.getDate());
				if (modelEvent.getDate().isBefore(LocalDate.now())) {
					displayMyItems(new Text(""));
				} else if (modelEvent.getDate().isAfter(LocalDate.now())) {
					displayMyItems(view8.returnTicketBtn());
				}
				//displayConfirmation();
			
			}
		});
	}
	
	private void displayTickets() {
		MainWindow.setCenter(view4.buyBox());
	}
	
	private void displayCheckout() {
		MainWindow.setCenter(view5.checkoutBox(new Text()));
	}
	
	private void displayReceipt() {
		MainWindow.setCenter(view6.receiptGrid());
	}
	
	private void displayMyAccount() {
		MainWindow.setLeft(null);
		MainWindow.setCenter(view9.getHyperlinkPane());
	}
	
	private void displayMyItems(Node n1) {
		MainWindow.setRight(view8.mainGrid(n1));
	}
	
	private void displayMyTickets() {
		VBox pane = new VBox();
		VBox headerPane = new VBox();
		Label header = new Label("This Venue's Events:");
		header.setFont(new Font(32));
		headerPane.getChildren().addAll(header);
		headerPane.setAlignment(Pos.TOP_CENTER);
		pane.setSpacing(5);
		pane.setPadding(new Insets(7.5, 0, 0, 0));
		pane.getChildren().addAll(headerPane, view.getMyEventsTable());
		
		MainWindow.setLeft(pane);
		MainWindow.setRight(null);
	}

	@Override
	public void update(Observable event, Object arg) {
		new ExpandEventController(new Pane4Event());
	}
	
}