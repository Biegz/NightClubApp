package controller;


import java.util.EventObject;

import model.Event;
import model.model4User.model4Customer.Customer;

public class SearchButtonEvent extends EventObject {

	private Event event;
	
	public SearchButtonEvent(Object source) {
		super(source);
	}
	
	public SearchButtonEvent(Object source, Event event) {
		super(source);
		this.event = event;
	}
	
	public Event getEvent() {
		return event;
	}
	
}

