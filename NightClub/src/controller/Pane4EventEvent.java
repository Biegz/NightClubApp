package controller;

import java.util.EventObject;

import model.Event;

public class Pane4EventEvent extends EventObject {

	private Event event;
	
	public Pane4EventEvent(Object source) {
		super(source);
	}
	
	public Pane4EventEvent(Object source, Event event) {
		super(source);
		this.event = event;
	}
	
	public Event getEvent() {
		return event;
	}
}
