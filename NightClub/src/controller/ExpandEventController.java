package controller;

import java.text.DecimalFormat;

import javafx.scene.layout.Pane;
import model.Event;
import model.model4User.model4Customer.Customer;
import view.MainWindow;
import view.Pane4Event;
import view.Pane4Events;
import view.Pane4Payment;
import view.Pane4TablesTickets;

public class ExpandEventController {

	private Pane4Events view;
	private Event model;
	private Customer model2;
	private MainWindow view2;
	private Pane4Event view3;
	private Pane4TablesTickets view4;
	private Pane4Payment view5;
	DecimalFormat df = new DecimalFormat("#.##");
	 
	public ExpandEventController(Pane4Events view) {
		this.view = view;
		this.view3 = new Pane4Event();
		this.view4 = new Pane4TablesTickets();
		this.view5 = new Pane4Payment();
		
		view.setPane4EventListener(new Pane4EventListener() {
			
			public void rowSelected(Pane4EventEvent ev) {
				model = ev.getEvent();
				Current.setEvent(model);
				view3.setTicketsLeft(model.getTicketsAvailable());
				view3.setTablesLeft(model.getTablesAvailable());
				view3.setDate(model.getDate(),model.getAddress());
				view3.setEventName(model.getEventName());
				view3.setImage("http://www.thegarden.com/content/dam/msg/eventImg3/Liberty_201718_328x253.jpg");
				displayEvent();
				  				
			}
		});
		
		view3.setPane4EventListener(new Pane4EventListener() {

			public void ticketsClicked(TicketButtonEvent ev) {
				model = ev.getEvent();
				int tempTickets = model.getTicketsAvailable();
				int tempTables = model.getTablesAvailable();
				
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
				
				view4.setTablePrice(model.getTablePrice());
				view4.setTicketPrice(model.getTicketPrice());

				view4.setTicketCostLabel(0.00);
				view4.setTableCostLabel(0.00);
				
				displayTickets();
			}
			
		});
		
		view4.setPane4EventListener(new Pane4EventListener() {
			
			public void checkoutClicked(CheckoutButtonEvent ev) {
				model = ev.getEvent();
				model2 = ev.getCustomer();
				int ticketAmount = ev.getTicketAmount();
				int tableAmount = ev.getTableAmount();
				double taxAmount = ((ticketAmount*model.getTicketPrice()) + (tableAmount*model.getTablePrice()))*(0.045);
				double totalAmount = taxAmount+ (ticketAmount*model.getTicketPrice()) + (tableAmount*model.getTablePrice());
				
//				view5.setStreetField(model2.getAddress().getNumber());
				view5.setAddressField(model2.getAddress().getStreet());
				view5.setCityField(model2.getAddress().getCity());
				view5.setStateCombo(model2.getAddress().getState());
				view5.setZipField(model2.getAddress().getZipcode());
				view5.setCardNameField(model2.getFirstName()+" "+model2.getLastName());
				view5.setCardNumberField("");
				view5.setTicketsDisplayLabel((ticketAmount)+" x $"+(df.format(model.getTicketPrice())));
				view5.setTablesDisplayLabel((tableAmount)+" x $"+(df.format(model.getTablePrice())));
				view5.setTaxDisplayLabel("$"+(df.format(taxAmount)));
				view5.setTotalDisplayLabel("$"+df.format(totalAmount));

				
				displayCheckout();
			}
		});
	}
	
	private void displayEvent() {
		view2.setCenter(view3.gridPane());
	}
	
	private void displayTickets() {
		view2.setCenter(view4.buyBox());
	}
	
	private void displayCheckout() {
		view2.setCenter(view5.checkoutBox());
	}
}