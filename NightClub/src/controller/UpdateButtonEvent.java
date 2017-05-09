package controller;

import java.util.EventObject;

import model.Event;

public class UpdateButtonEvent extends EventObject {

	private Event event;
	
	public UpdateButtonEvent(Object source) {
		super(source);
	}
	
	public UpdateButtonEvent(Object source, Event event) {
		super(source);
		this.event = event;

	}
	
	public Event getEvent(){
		return event;
	}
	
	

}