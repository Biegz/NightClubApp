package controller;

import java.util.EventObject;

import model.Business;
import model.Customer;
import model.Event;

public class PlaceOrderEvent extends EventObject{

	private Event event;
	private Customer customer;
	private Business business;
	private int tableCount;
	private int ticketCount;
	

	public PlaceOrderEvent(Object source) {
		super(source);
	}

	public PlaceOrderEvent(Object source, Event event, Customer customer, Business business, int ticketCount, int tableCount) {
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
