package controller;

import java.util.EventObject;

import model.Event;
import model.Ticket;
import model.model4User.model4Customer.Customer;

public class ClickEventEvent extends EventObject {
	private Event event;
	private Customer customer;
	
	public ClickEventEvent(Object source) {
		super(source);
	}
	
	public ClickEventEvent(Object source, Customer customer, Event event) {
		super(source);
		this.event = event;
		this.customer = customer;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public Customer getCustomer() {
		return customer;
	}

}
