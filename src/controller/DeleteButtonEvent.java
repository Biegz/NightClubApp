package controller;

import java.util.EventObject;

import model.Event;

public class DeleteButtonEvent extends EventObject {

	private Event event;
	
	public DeleteButtonEvent(Object source) {
		super(source);
	}
	
	public DeleteButtonEvent(Object source, Event event) {
		super(source);
		this.event = event;

	}
	
	public Event getEvent(){
		return event;
	}
	
	

}