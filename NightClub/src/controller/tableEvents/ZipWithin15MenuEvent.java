package controller.tableEvents;

import java.util.EventObject;

import model.Event;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;

public class ZipWithin15MenuEvent extends EventObject {
		private Customer customer;
		
		public ZipWithin15MenuEvent(Object source) {
			super(source);
		}
		
		public ZipWithin15MenuEvent(Object source, Customer customer) {
			super(source);
			this.customer = customer;
		}
		
		public Customer getCustomer() {
			return customer;
		}
}
