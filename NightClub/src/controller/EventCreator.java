package controller;

import java.util.Scanner;

import model.Event;
import model.model4Address.Address;
import view.Pane4EventCreation;

public class EventCreator {

	private Event event;

	public EventCreator() {
		event = new Event();
	}
	
	public void createEvent() {
		event = new Event();
		event.setEventName(Pane4EventCreation.nameField.getText());
		event.setDescription(Pane4EventCreation.descriptionField.getText());
		Address tempAddress = new Address();
		Scanner sc = new Scanner(Pane4EventCreation.addressField.getText());
		tempAddress.setStreet(Pane4EventCreation.addressField.getText());
		tempAddress.setCity(Pane4EventCreation.cityField.getText());
		tempAddress.setState((String) Pane4EventCreation.stateField.getSelectionModel().getSelectedItem());
		tempAddress.setZipcode(Pane4EventCreation.zipField.getText());
		event.setAddress(tempAddress);
		event.setDate(Pane4EventCreation.dateField.getValue());
		event.setTicketPrice(Double.parseDouble(Pane4EventCreation.ticketPriceField.getText()));
		event.setTablePrice(Double.parseDouble(Pane4EventCreation.tablePriceField.getText()));
		event.setTotalTickets(Integer.parseInt(Pane4EventCreation.totalTicketsField.getText()));
		event.setTotalTables(Integer.parseInt(Pane4EventCreation.totalTablesField.getText()));
	}

	public void setName(String name) {
		event.setEventName(name);
	}

	public void setTotalTickets(int total) {
		event.setTotalTickets(total);
	}

	public void setTotalTables(int total) {
		event.setTotalTables(total);
	}

	public void setDescription(String description) {
		event.setDescription(description);
	}

	public void setGenre(String genre) {
		event.setGenre(genre);
	}

	public void setTicketPrice(double price) {
		event.setTicketPrice(price);
	}

	public void setTableePrice(double price) {
		event.setTablePrice(price);
	}

}
