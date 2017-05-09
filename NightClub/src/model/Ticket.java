package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Ticket implements Serializable {

	private Event event;
	private Double cost;
	private LocalDate date;
	
	public Ticket(Event event) {
		this.event = event;
		this.cost = event.getTicketPrice();
		this.date = event.getDate();
	}

	public String getEventName() {
		return event.getEventName();
	}
	
	public Event getEvent(){
		return event;
	}

	public Double getCost() {
		return cost;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public String displayInfo(){
		String info = new String(event.toString());
		return info;

	}
}
