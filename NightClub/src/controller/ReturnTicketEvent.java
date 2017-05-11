package controller;

import java.util.EventObject;

import model.Event;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;

public class ReturnTicketEvent extends EventObject {

	private Event event;
	private Customer customer;
	private Business business;
	private int ticketCount;
	

	public ReturnTicketEvent(Object source) {
		super(source);
	}

	public ReturnTicketEvent(Object source, Event event, Customer customer, int ticketCount){
		super(source);
		this.event = event;
		this.customer = customer;
		this.ticketCount = ticketCount;

	}
	
	public Event getEvent(){
		return event;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public int getTicketCount() {
		return ticketCount;
	}
	

}
