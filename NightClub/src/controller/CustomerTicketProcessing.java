package controller;

import java.util.ArrayList;

import model.Event;
import model.EventsBag;
import model.Table;
import model.Ticket;
import model.UsersBag;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;

public class CustomerTicketProcessing {

	private Event event;
	private Customer customer;

	public CustomerTicketProcessing(Event event) {
		this.event = event;
	}
	
	public void cancelEvent(Event event){
		ArrayList<Customer> attending = event.getAttending();
		Business currentBusiness = event.getBusiness();
		for(Customer c : attending){
			Ticket temp = c.findTicket(event);
			Table tempTable = c.findTable(event);
			if (temp != null) {
				c.addBalance(temp.getCost() + (.045*temp.getCost()));
				currentBusiness.getFinanceInfo().removeSale(temp);
				c.removeTicket(temp);
			} else {
				c.addBalance(tempTable.getCost() + (.045*temp.getCost()));
				currentBusiness.getFinanceInfo().removeSale(tempTable);
				c.removeTable(tempTable);
			}
		}
		currentBusiness.removeEvent(event);
		EventsBag.delete(event);
	}
	
	public void returnTicket(Ticket ticket, Customer currentCustomer){

		Event temp = null;
		temp = ticket.getEvent();
		
		temp.getBusiness().getFinanceInfo().removeSale(ticket);
		currentCustomer.addBalance(ticket.getCost() + (.045*ticket.getCost()));		

		temp.removeCustomer(currentCustomer);
		currentCustomer.removeTicket(ticket);
	}

	public void buyTicket(int amount, Customer customer, Event event, Business business) {
		if (amount < 1) {
			return;
		} else {
			if (amount <= event.getTicketsAvailable()) {
				finalizeTransaction(amount, customer, event, business);
			} else {
				System.out.println("Not enough tickets available");
			}
		}
	}

	public void finalizeTransaction(int amount, Customer customer, Event event, Business business) {
		//double totalSalesTax = amount * (.045*event.getTicketPrice());
		double totalCost = amount * (event.getTicketPrice());
			for (int i = 0; i < amount; i++) {
				customer.addTicket(new Ticket(event));
				Customer tempCustomer = customer;
				event.addCustomer(tempCustomer);
			}
			business.getFinanceInfo().addSale(totalCost, 0);
			event.setTicketsAvailable(event.getTicketsAvailable() - amount);
		}
	
	public void buyTable(int amount, Customer customer, Event event, Business business) {
		//double totalSalesTax = amount *  (.045 *event.getTablePrice());
		double totalCost = amount * (event.getTablePrice());
		for (int i = 0; i < amount; i++) {
			customer.addTable(new Table(Current.getEvent()));
			event.addCustomer(customer);
		}
			business.getFinanceInfo().addSale(totalCost, 0);
			event.setTablesAvailable(event.getTablesAvailable() - amount);
			IO.saveAll();
	}
	
	public void returnTable(Table table, Customer currentCustomer) {
		Event temp = null;
		temp = table.getEvent();
		// Overload remove sale method (change args from double to ticket/table
		// then, in method get ticket.getCost or table.getCost()
		temp.getBusiness().getFinanceInfo().removeSale(table);
		currentCustomer.addBalance(table.getCost() + (.045*table.getCost()));		
		// Need write remove table method
		temp.removeCustomer(currentCustomer);
		currentCustomer.removeTable(table);
		IO.saveAll();
	}


}

