package controller;

import java.util.EventObject;

import model.Event;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;

public class PlaceOrderEvent extends EventObject{

	private Event event;
	private Customer customer;
	private Business business;
	private int tableCount;
	private int ticketCount;
	

	public PlaceOrderEvent(Object source) {
		super(source);
	}

	public PlaceOrderEvent(Object source, Event event, Customer customer, Business business, int tableCount, int ticketCount) {
		super(source);
		this.event = event;
		this.customer = customer;
		this.business = business;
		this.tableCount = tableCount;
		this.ticketCount = ticketCount;

	}
	
	public Event getEvent(){
		return event;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Business getBusiness() {
		return business;
	}
	
	public int getTableCount() {
		return tableCount;
	}
	
	public int getTicketCount() {
		return ticketCount;
	}
	

}
