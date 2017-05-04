package controller;

import javafx.scene.control.Label;
import model.Event;
import model.EventsBag;
import model.model4Address.Address;
import view.MainWindow;
import view.Pane4Events;

public class EventController {

	private Pane4Events view;
	private Pane4Events view2;
	private Pane4Events view3;

	
	private MainWindow viewMain;
	private Event model;
	private EventsBag modelBag;
	private Address modelAddress;

	public EventController(Pane4Events view) {
		this.view = view;
		this.view2 = new Pane4Events();
		this.view3 = new Pane4Events();

		view.setCreateEventListener(new CreateEventListener() {

			@Override
			public void createButtonClicked(Pane4EventsEvent ev) {
				System.out.print("In controller");
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
		
		view2.setDeleteEventListener(new DeleteEventListener(){
			
			@Override
			public void deleteButtonClicked(Pane4EventsEvent ev){
				model = ev.getEvent();
				modelBag.delete(model);
				System.out.println("reached delete");
				//need to refresh observ list that populates myeventstable
				view.getMyEventsTable().refresh();
				
				emptyPane();
			}
		});
		
		view3.setUpdateEventListener(new UpdateEventListener(){

			@Override
			public void updateButtonClicked(Pane4EventsEvent ev) {
				
			}
			
		});
		
	
		
		
		
		
		
		
		

	}

	public void emptyPane() {
		viewMain.setCenter(new Label());
	}
	
	public void showEvent() {
		System.out.println(model.getEventName());
	}
	

}
