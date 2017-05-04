package controller;

import model.Event;
import model.model4Address.Address;
import view.Pane4Events;

public class EventController {

	private Pane4Events view;
	private Event model;
	private Address modelAddress;
	
	public EventController(Pane4Events view){
		this.view = view;
		view.setPane4EventsListener(new Pane4EventsListener(){
			
			@Override
			public void buttonClicked(Pane4EventsEvent ev){
				model = ev.getEvent();
				modelAddress = new Address();
				
				model.setBusiness(ev.getEvent().getBusiness());
				model.setEventName(ev.getEvent().getEventName());
				model.setGenre(ev.getEvent().getGenre());
				model.setDescription(ev.getEvent().getDescription());
				
				model.setAddress(ev.getEvent().getAddress());
				
//				modelAddress.setCity(ev.getEvent().getAddress().getCity());
//				modelAddress.setState(ev.getEvent().getAddress().getState());
//				modelAddress.setStreet(ev.getEvent().getAddress().getStreet());
//				modelAddress.setNumber(ev.getEvent().getAddress().getNumber());
//				modelAddress.setZipcode(ev.getEvent().getAddress().getZipcode());
//				model.setAddress(modelAddress);
				
				
				
				model.setDate(ev.getEvent().getDate());
				model.setTotalTickets(ev.getEvent().getTotalTickets());
				model.setTicketPrice(ev.getEvent().getTicketPrice());
				model.setTotalTables(ev.getEvent().getTotalTables());
				model.setTablePrice(ev.getEvent().getTablePrice());
				showEvent();
				
				
			}
			
			
		});
	
}
	
	
	
	public void showEvent(){
		System.out.println(model.getEventName());
	}
}
