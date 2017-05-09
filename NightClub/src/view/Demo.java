package view;

import controller.IO;
import controller.SignInUp;
import model.Event;
import model.model4User.model4Customer.Customer;

public class Demo {

	public static void main(String[] args) {

		
		Event ev1 = new Event();
		ev1.setEventName("Dance");
		Customer c1 = new Customer("John", 45);
		c1.setFirstName("John");
		ev1.addCustomer(c1);
		for(Customer c: ev1.getCustomerList()) {
			System.out.println(c.getFirstName());
		}
	}

}
