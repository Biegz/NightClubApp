package controller;


import java.util.EventObject;

import model.Event;
import model.model4User.model4Customer.Customer;

public class SearchButtonEvent extends EventObject {

	private String venue;
	
	public SearchButtonEvent(Object source) {
		super(source);
	}
	
	public SearchButtonEvent(Object source, String venue) {
		super(source);
		this.venue = venue;
	}
	
	public String getVenueSearch() {
		return venue;
	}
	
}

