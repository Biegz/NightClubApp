package model;

import java.io.Serializable;

public class Table implements Serializable {

	Event event;
	double cost;
	
	public Table(Event event) {
		this.event = event;
		this.cost = event.getTablePrice();
	}

	public String getEventName() {
		return event.getEventName();
	}
	
	public Event getEvent() {
		return event;
	}

//	public double getPrice() {
//		return event.getTicketPrice();
//	}
	
	public double getCost() {
		return cost;
	}
	
	public String displayInfo(){
		String info = new String(event.toString());
		return info;
	}
}
