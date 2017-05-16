package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.Pane4EventEvent;
import controller.Pane4EventListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Business;
import model.Customer;
import model.Event;
import model.EventsBag;
import view.MainWindow;
import view.Pane4Table;

public class TableTranslator {

	private TableView<Event> eventsTable;
	private ObservableList<Event> events;
	private Pane4EventListener pane4EventListener;
	private Pane4Table view;
	private EventsBag eventsBag;
	private Customer customer;
	public ArrayList<Event> masterList;

	public TableTranslator() {
	}
	
	public ArrayList<Event> getRecommendedEvents(Customer customer){
		masterList = new ArrayList<>();
		for(Event e: eventsBag.events){
			if(e.getDate().isAfter(LocalDate.now())){
				if(e.getGenre().equals(customer.getFavGenre())){
					masterList.add(e);
				}
			}
			
		}
		return masterList;
	}

	public ArrayList<Event> getAllEvents() {
		masterList = new ArrayList<>();
		for(Event e: eventsBag.events){
			if(e.getDate().isAfter(LocalDate.now())){
				masterList.add(e);
			}
		}
		return masterList;
	}

	public ArrayList<Event>  getMyEvents(Business business) {
		masterList = new ArrayList<>();
		for(Event ev : eventsBag.events){
			if(ev.getBusiness().getUsername().equals(business.getUsername())){
				masterList.add(ev);
			}
		}
		return masterList;
	}

	public ArrayList<Event>  getByZip15(Customer customer) {
		masterList = new ArrayList<>();
		for(Event e : eventsBag.events){
			if(e.getDate().isAfter(LocalDate.now())){
				if(e.getAddress().getZipcode().charAt(2) == customer.getAddress().getZipcode().charAt(2)){
					masterList.add(e);			
					}
			}
			
		}
		return masterList;
	}
	
	public ArrayList<Event>  getByZip50(Customer customer) {
		masterList = new ArrayList<>();
		for(Event e: eventsBag.events){
			if(e.getDate().isAfter(LocalDate.now())){
				if(e.getAddress().getZipcode().charAt(1) == customer.getAddress().getZipcode().charAt(1)){
					masterList.add(e);
				}
			}
			
		}
		return masterList;
	}
	
	
	
	
	public ArrayList<Event> getMyUpcomingEvents(Customer customer) {
		masterList = new ArrayList<>();
		for(Event e: customer.getEventList()) {
			if(e.getDate().isAfter(LocalDate.now())) {
				masterList.add(e);
			}
		}
		return masterList;
	}
	
	public ArrayList<Event> getMyPastEvents(Customer customer) {
		masterList = new ArrayList<>();
		for(Event e: customer.getEventList()) {
			if(e.getDate().isBefore(LocalDate.now())) {
				masterList.add(e);
			}
		}
		return masterList;
	}
}
