package controller;

import java.util.EventObject;

import model.Customer;
import model.Event;
import model.Ticket;

public class MyOrderEvent extends EventObject {
	private Customer customer;

	public MyOrderEvent(Object source) {
		super(source);
	}
	
	public MyOrderEvent(Object source, Customer customer) {
		super(source);
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}

}
