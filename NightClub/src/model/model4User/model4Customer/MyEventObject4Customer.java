package model.model4User.model4Customer;

import java.util.EventObject;

public class MyEventObject4Customer extends EventObject {

	private Customer customer;
	
	public MyEventObject4Customer(Object source) {
		super(source);
	}
	
	public MyEventObject4Customer(Object source, Customer customer){
		super(source);
		this.customer = customer;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	

}
