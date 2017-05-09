package model;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

	private Event event;
	private double cost;
	
	public Ticket(Event event) {
		this.event = event;
		this.cost = event.getTicketPrice();
	}

	public String getEventName() {
		return event.getEventName();
	}
	
	public Event getEvent(){
		return event;
	}

	public double getCost() {
		return cost;
	}
	
	public String displayInfo(){
		String info = new String(event.toString());
		return info;

	}
}
