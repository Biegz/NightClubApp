package controller.tableEvents;

import java.util.EventObject;

import model.Event;
import model.model4User.model4Establishment.Business;

public class MyEventsMenuEvent extends EventObject {
	
	private Business business;
	
	
	public MyEventsMenuEvent(Object source) {
		super(source);
	}
	
	public MyEventsMenuEvent(Object source, Business business) {
		super(source);
		this.business = business;
	}
	
	public Business getBusiness() {
		return business;
	}
}

