package controller;

import java.util.ArrayList;

import model.Business;
import model.Customer;
import model.Event;
import model.EventsBag;
import model.Table;
import model.Ticket;
import model.UsersBag;

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
		Event temp = ticket.getEvent();
		temp.getBusiness().getFinanceInfo().removeSale(ticket);
		ticket.getEvent().setTicketsAvailable(ticket.getEvent().getTicketsAvailable()+1);
		temp.removeCustomer(currentCustomer);
	}
	
	public void returnTable(Table table, Customer currentCustomer) {
		Event temp = table.getEvent();
		temp.getBusiness().getFinanceInfo().removeSale(table);
		table.getEvent().setTablesAvailable(table.getEvent().getTablesAvailable()+1);
		temp.removeCustomer(currentCustomer);
		IO.saveAll();
	}


	public void buyTicket(int amount, Customer customer, Event event, Business business) {
		if (amount < 1) {
			return;
		} else {
			finalizeTransaction(amount, customer, event, business);
		}
	}

	public void finalizeTransaction(int amount, Customer customer, Event event, Business business) {
		double totalCost = amount * (event.getTicketPrice());
			for (int i = 0; i < amount; i++) {
				customer.addTicket(new Ticket(event));
				Customer tempCustomer = customer;
				event.addCustomer(tempCustomer);
				customer.setEventList(event);
			}
			
			business.getFinanceInfo().addSale(totalCost, 0);
			event.setTicketsAvailable(event.getTicketsAvailable() - amount);
		}
	
	public void buyTable(int amount, Customer customer, Event event, Business business) {
		double totalCost = amount * (event.getTablePrice());
		for (int i = 0; i < amount; i++) {
			customer.addTable(new Table(event));
			Customer tempCustomer = customer;
			event.addCustomer(tempCustomer);
		}
		
			business.getFinanceInfo().addSale(totalCost, 0);
			event.setTablesAvailable(event.getTablesAvailable() - amount);
			IO.saveAll();
	}


}

