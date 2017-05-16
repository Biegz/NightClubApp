package controller;


import java.util.EventObject;

import model.Event;

public class TicketButtonEvent extends EventObject {

	private Event event;
	
	public TicketButtonEvent(Object source) {
		super(source);
	}
	
	public TicketButtonEvent(Object source, Event event) {
		super(source);
		this.event = event;
	}
	
	public Event getEvent() {
		return event;
	}
	
}


