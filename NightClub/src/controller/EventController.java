package controller;

import javafx.scene.control.Label; 
import model.Event;
import model.EventsBag;
import model.model4Address.Address;
import view.MainWindow;
import view.Pane4EditEvents;
import view.Pane4EventCreation;
import view.Pane4Events;

public class EventController {

	private Pane4EventCreation view;

	
	private Event modelEvent;
	
	private EventsBag modelBag;
	private Address modelAddress;

	public EventController(Pane4EventCreation view) {
		this.view = view;

		System.out.println("In the controller");
		view.setEventsListener(new EventsListener() {
			@Override
			public void createButtonClicked(CreateButtonEvent ev) {
				
				System.out.println("Im in boys");
				modelEvent = ev.getEvent();
				modelAddress = new Address();

				modelEvent.setBusiness(ev.getEvent().getBusiness());
				modelEvent.setEventName(ev.getEvent().getEventName());
				modelEvent.setGenre(ev.getEvent().getGenre());
				modelEvent.setDescription(ev.getEvent().getDescription());

				modelEvent.setAddress(ev.getEvent().getAddress());

				// modelAddress.setCity(ev.getEvent().getAddress().getCity());
				// modelAddress.setState(ev.getEvent().getAddress().getState());
				// modelAddress.setStreet(ev.getEvent().getAddress().getStreet());
				// modelAddress.setNumber(ev.getEvent().getAddress().getNumber());
				// modelAddress.setZipcode(ev.getEvent().getAddress().getZipcode());
				// model.setAddress(modelAddress);

				modelEvent.setDate(ev.getEvent().getDate());
				modelEvent.setTotalTickets(ev.getEvent().getTotalTickets());
				modelEvent.setTicketPrice(ev.getEvent().getTicketPrice());
				modelEvent.setTotalTables(ev.getEvent().getTotalTables());
				modelEvent.setTablePrice(ev.getEvent().getTablePrice());
				
				modelBag.add(modelEvent);
				modelBag.save();
				
				// change mainpain.setcenter 
				emptyPane();
				showEvent();

			
		
			
			
			}

			});
		
		view.setEventsListener(new EventsListener(){
			
			@Override
			public void deleteButtonClicked(DeleteButtonEvent ev){
				modelEvent = ev.getEvent();
				modelBag.delete(modelEvent);
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
		MainWindow.setCenter(null);
	}
	
	public void showEvent() {
		System.out.println(modelEvent.getEventName());
	}
	


}
