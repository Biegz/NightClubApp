package controller;

import java.util.EventObject;

import model.Event;

public class CreateButtonEvent extends EventObject {

	private Event event;
	
	public CreateButtonEvent(Object source) {
		super(source);
	}
	
	public CreateButtonEvent(Object source, Event event) {
		super(source);
		this.event = event;

	}
	
	public Event getEvent(){
		return event;
	}
	
	

}
