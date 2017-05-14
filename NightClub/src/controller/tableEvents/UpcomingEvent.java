package controller.tableEvents;

import java.util.EventObject;

import model.model4User.model4Customer.Customer;

public class UpcomingEvent extends EventObject {
	private Customer customer;
	
	public UpcomingEvent(Object source) {
		super(source);
	}
	
	public UpcomingEvent(Object source, Customer customer) {
		super(source);
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
}
