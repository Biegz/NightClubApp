package controller;

import java.util.ArrayList;

import model.Event;
import model.EventsBag;
import model.Table;
import model.Ticket;
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

	public void buyTicket(int amount) {
		if (amount < 1) {
			return;
		} else {
			if (amount <= event.getTicketsAvailable()) {
				finalizeTransaction(amount, Current.getCustomer(), Current.getEvent());
			} else {
				System.out.println("Not enough tickets available");
			}
		}
	}

	public void finalizeTransaction(int amount, Customer customer, Event event) {
		double totalSalesTax = amount * (.045*event.getTicketPrice());
		double totalCost = amount * (event.getTicketPrice());
		if (totalCost+totalSalesTax > customer.getBalance()) {
			System.out.println("Not enough money");
		} else {
			for (int i = 0; i < amount; i++) {
				customer.addTicket(new Ticket(event));
				event.addCustomer(customer);
			}
			customer.setBalance((customer.getBalance()) - totalCost - totalSalesTax);
			event.getBusiness().getFinanceInfo().addSale(totalCost, 0);
		}
	}
	
	public void buyTable() {
		Event tempEvent = Current.getEvent();
		Customer tempCustomer = Current.getCustomer();
		double totalSalesTax = (.045 *tempEvent.getTablePrice());
		double totalCost = (tempEvent.getTablePrice());
		if (totalCost+totalSalesTax > tempCustomer.getBalance()) {
			//view.setLabel("Transaction cost exceeds available balance.");
		}
		else {
			// need write addTable to customer and event
			tempCustomer.addTable(new Table(Current.getEvent()));
			tempEvent.addCustomer(tempCustomer);
			tempCustomer.setBalance(tempCustomer.getBalance() - totalCost - totalSalesTax);
			tempEvent.getBusiness().getFinanceInfo().addSale(totalCost, 0);
		}
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
	}

	// ASK CHEN ABOUT RESERVATIONS

}

