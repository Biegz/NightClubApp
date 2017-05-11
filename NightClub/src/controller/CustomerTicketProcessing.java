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
		Event temp = ticket.getEvent();
		temp.getBusiness().getFinanceInfo().removeSale(ticket);
		
		System.out.println("Tickets " + ticket.getEvent().getTicketsAvailable());
		ticket.getEvent().setTicketsAvailable(ticket.getEvent().getTicketsAvailable()+1);
		System.out.println("Tickets Now: " + ticket.getEvent().getTicketsAvailable());
		
		//need to change tickets available^^
		temp.removeCustomer(currentCustomer);
		//currentCustomer.removeTicket(ticket);
		
	}

	public void buyTicket(int amount, Customer customer, Event event, Business business) {
		if (amount < 1) {
			return;
		} else {
			finalizeTransaction(amount, customer, event, business);
		}
	}

	public void finalizeTransaction(int amount, Customer customer, Event event, Business business) {
		//double totalSalesTax = amount * (.045*event.getTicketPrice());
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
			System.out.println("Tables: "+ event.getTablesAvailable());
			event.setTablesAvailable(event.getTablesAvailable() - amount);
			System.out.println("Tables Now: "+ event.getTablesAvailable());
			IO.saveAll();
	}
	
	public void returnTable(Table table, Customer currentCustomer) {
		Event temp = null;
		temp = table.getEvent();
		temp.getBusiness().getFinanceInfo().removeSale(table);
		
		System.out.println("Tables " + temp.getTablesAvailable());
		table.getEvent().setTablesAvailable(temp.getTablesAvailable()+1);
		System.out.println("Tables Now: " + temp.getTablesAvailable());
		
		temp.removeCustomer(currentCustomer);
		//currentCustomer.removeTable(table);
		IO.saveAll();
	}


}

