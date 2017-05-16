package controller;

import java.util.EventObject;

import model.Customer;
import model.Event;

public class CheckoutButtonEvent extends EventObject {

	private Event event;
	private Customer customer;
	private int ticketAmount;
	private int tableAmount;

	public CheckoutButtonEvent(Object source) {
		super(source);
	}
	
	public CheckoutButtonEvent(Object source, Event event, Customer customer, int ticketAmount, int tableAmount) {
		super(source);
		this.event = event;
		this.customer = customer;
		this.ticketAmount = ticketAmount;
		this.tableAmount = tableAmount;
	}
	
	public int getTicketAmount() {
		return ticketAmount;
	}
	
	public int getTableAmount() {
		return tableAmount;
	}

	public Event getEvent() {
		return event;
	}
	
	public Customer getCustomer() {
		return customer;
	}
}
