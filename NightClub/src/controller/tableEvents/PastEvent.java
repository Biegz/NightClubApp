package controller.tableEvents;

import java.util.EventObject;

import model.model4User.model4Customer.Customer;

public class PastEvent extends EventObject{
	private Customer customer;
	
	public PastEvent(Object source) {
		super(source);
	}
	
	public PastEvent(Object source, Customer customer) {
		super(source);
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
}
