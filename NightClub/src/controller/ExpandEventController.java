package controller;

import java.text.DecimalFormat;
import java.time.LocalDate;

import javafx.scene.layout.Pane;
import model.Event;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;
import view.MainWindow;
import view.Pane4Event;
import view.Pane4Events;
import view.Pane4Payment;
import view.Pane4Receipt;
import view.Pane4TablesTickets;

public class ExpandEventController {

	private Pane4Events view;
	private Event modelEvent;
	private Customer modelCustomer;
	private Business modelBusiness;
	//private MainWindow view2;
	private Pane4Event view3;
	private Pane4TablesTickets view4;
	private Pane4Payment view5;
	private Pane4Receipt view6;
	DecimalFormat df = new DecimalFormat("#.##");
	 
	public ExpandEventController(Pane4Events view) {
		this.view = view;
		this.view3 = new Pane4Event();
		this.view4 = new Pane4TablesTickets();
		this.view5 = new Pane4Payment();
		this.view6 = new Pane4Receipt();
		
		view.setPane4EventListener(new Pane4EventListener() {
			
			public void rowSelected(Pane4EventEvent ev) {
				modelEvent = ev.getEvent();
				Current.setEvent(modelEvent);
				view3.setTicketsLeft(modelEvent.getTicketsAvailable());
				view3.setTablesLeft(modelEvent.getTablesAvailable());
				view3.setDate(modelEvent.getDate(),modelEvent.getAddress());
				view3.setEventName(modelEvent.getEventName());
				view3.setImage("http://www.thegarden.com/content/dam/msg/eventImg3/Liberty_201718_328x253.jpg");
				displayEvent();
				  				
			}
			
		});
		
		view3.setPane4EventListener(new Pane4EventListener() {

			public void ticketsClicked(TicketButtonEvent ev) {
				modelEvent = ev.getEvent();
				Current.setPreviousPane(view3.gridPane());
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
				int ticketAmount = ev.getTicketCount();
				int tableAmount = ev.getTableCount();
				double taxAmount = ((ticketAmount*modelEvent.getTicketPrice()) + (tableAmount*modelEvent.getTablePrice()))*(0.045);
				double totalAmount = taxAmount+ (ticketAmount*modelEvent.getTicketPrice()) + (tableAmount*modelEvent.getTablePrice());

				CustomerTicketProcessing ticketProcessor = new CustomerTicketProcessing(modelEvent);
				ticketProcessor.buyTicket(ticketAmount, modelCustomer, modelEvent, modelBusiness);
				ticketProcessor.buyTable(ticketAmount, modelCustomer, modelEvent, modelBusiness);
				
				view6.setEventLabel(modelEvent.getEventName());
				view6.setReceiptLabel(modelCustomer.getFirstName());
				view6.setDateLabel(LocalDate.now());
				view6.setTicketLabel(ev.getTicketCount(), modelEvent.getTicketPrice());
				view6.setTableLabel(ev.getTableCount(), modelEvent.getTablePrice());
				view6.setTaxLabel(taxAmount);
				view6.setTotalLabel(totalAmount);
				displayReceipt();
				
			}
		});
	}
	
	private void displayEvent() {
		MainWindow.setCenter(view3.gridPane());
	}
	
	private void displayTickets() {
		MainWindow.setCenter(view4.buyBox());
	}
	
	private void displayCheckout() {
		MainWindow.setCenter(view5.checkoutBox());
	}
	
	private void displayReceipt() {
		MainWindow.setCenter(view6.receiptGrid());
	}
}