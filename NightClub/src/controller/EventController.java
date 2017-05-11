package controller;

import javafx.scene.control.Label; 
import model.Event;
import model.EventsBag;
import model.model4Address.Address;
import view.MainWindow;
import view.Pane4EditEvents;
import view.Pane4Events;

public class EventController {

	private Pane4EditEvents view;

	private MainWindow viewMain;
	private Event model;
	private EventsBag modelBag;
	private Address modelAddress;

	public EventController(Pane4EditEvents view) {
		this.view = view;
		
		
		
		System.out.println("In the Controller!");

		view.setEventsListener(new EventsListener() {

			@Override
			public void createButtonClicked(CreateButtonEvent ev) {
				
				model = ev.getEvent();
				modelAddress = new Address();

				model.setBusiness(ev.getEvent().getBusiness());
				model.setEventName(ev.getEvent().getEventName());
				model.setGenre(ev.getEvent().getGenre());
				model.setDescription(ev.getEvent().getDescription());

				model.setAddress(ev.getEvent().getAddress());

				// modelAddress.setCity(ev.getEvent().getAddress().getCity());
				// modelAddress.setState(ev.getEvent().getAddress().getState());
				// modelAddress.setStreet(ev.getEvent().getAddress().getStreet());
				// modelAddress.setNumber(ev.getEvent().getAddress().getNumber());
				// modelAddress.setZipcode(ev.getEvent().getAddress().getZipcode());
				// model.setAddress(modelAddress);

				model.setDate(ev.getEvent().getDate());
				model.setTotalTickets(ev.getEvent().getTotalTickets());
				model.setTicketPrice(ev.getEvent().getTicketPrice());
				model.setTotalTables(ev.getEvent().getTotalTables());
				model.setTablePrice(ev.getEvent().getTablePrice());
				
				// change mainpain.setcenter 
				emptyPane();
				showEvent();

			
		
			
			
			}

			});
		
		view.setEventsListener(new EventsListener(){
			
			@Override
			public void deleteButtonClicked(DeleteButtonEvent ev){
				model = ev.getEvent();
				modelBag.delete(model);
				System.out.println("reached delete");
			
				emptyPane();
			}
		});
		
		view.setEventsListener(new EventsListener(){

			@Override
			public void updateButtonClicked(UpdateButtonEvent ev) {
				
			}
			
		});
		
		view.setEventsListener(new EventsListener(){
			
		});
		
	
		
		
		
		
		
		
		

	}

	public void emptyPane() {
		viewMain.setCenter(new Label());
	}
	
	public void showEvent() {
		System.out.println(model.getEventName());
	}
	


}
