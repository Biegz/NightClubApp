package controller;

import javafx.scene.layout.Pane;
import model.Business;
import model.Customer;
import model.Event;
import model.User;

public class Current {
	private static Current current = new Current();
	private static User user;
	private static Customer customer;
	private static Business business;
	private static Event event;
	private static Pane pane;
	private static Pane previousPane;
	
	public Current(){
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Current.user = user;
		if (user instanceof Customer){
			Current.customer = (Customer) user;
		} else {
			Current.business = (Business) user;
		}
	}

	public static Event getEvent() {
		return event;
	}

	public static void setEvent(Event event) {
		Current.event = event;
	}

	public static Pane getPane() {
		return pane;
	}

	public static void setPane(Pane pane) {
		Current.pane = pane;
	}

	public static Pane getPreviousPane() {
		return previousPane;
	}

	public static void setPreviousPane(Pane previousPane) {
		Current.previousPane = previousPane;
	}

	public static Customer getCustomer() {
		return customer;
	}
	
	public static void setBusiness(Business buss) {
		Current.business = buss;
	}
	
	public static Business getBusiness() {
		return business;
	}
	
	
	
}
