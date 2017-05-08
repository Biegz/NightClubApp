package controller;

import java.util.EventObject;

import model.Event;

public class Pane4EventsEvent extends EventObject {

	private Event event;

	public Pane4EventsEvent(Object source) {
		super(source);
	}

	public Pane4EventsEvent(Object source, Event event) {
		super(source);
		this.event = event;

	}

	public Event getEvent() {
		return event;
	}

}
