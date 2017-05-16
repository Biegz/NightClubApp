package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import controller.Current;

public class EventsBag implements Serializable {
	
	private static EventsBag eventsBag = new EventsBag();
	public static ArrayList<Event> events = new ArrayList<>();
	
	public EventsBag() {
	}

	public static void add(Event event) {
		events.add(event);
	}
	
	public static void displayEventsBag() {
		for (Event u: events) {
			System.out.println(u.toString());
		}
	}

	public static EventsBag searchEventByName(String eventName) {
		eventName = eventName.toLowerCase();
		EventsBag nameEvents = new EventsBag();
		for (Event ev : events) {
			if (ev.getEventName().toLowerCase().contains(eventName)){
				nameEvents.add(ev);
			}
		}
		return nameEvents;
	}
	

	public static ArrayList<Event> getCurrentBusinessEvents(){
		ArrayList<Event> currentBusinessEvents = new ArrayList<>();
		for(Event ev : events){
			if(ev.getBusiness().getUsername().equals(Current.getBusiness().getUsername())){
				currentBusinessEvents.add(ev);
			}
		}
		return currentBusinessEvents;
		
	}
	
	public static EventsBag searchEventByGenre(Genre genre) {
		EventsBag genreEvents = new EventsBag();
		for (Event ev: events) {
			if (ev.getGenre() == genre) {
				genreEvents.add(ev);
			}
		}
		return genreEvents;
	}
	
	public static ArrayList<Event> getEventsWithin15(){
		ArrayList<Event> zipEvents = new ArrayList<>();
		for(Event ev : events){
			if(ev.getAddress().getZipcode().charAt(2) == Current.getCustomer().getAddress().getZipcode().charAt(2)){
				zipEvents.add(ev);
			}
		}
		return zipEvents;
	}
	
	public static ArrayList<Event> getEventsWithin50(){
		ArrayList<Event> zipEvents = new ArrayList<>();
		for(Event ev : events){
			if(ev.getAddress().getZipcode().charAt(1) == Current.getCustomer().getAddress().getZipcode().charAt(1)){
				zipEvents.add(ev);
			}
		}
		return zipEvents;
	}

	
	
	public static void delete(Event event) {
		events.remove(event);
	}

	
	public static void save() {
		try {
			FileOutputStream fileOutStream = new FileOutputStream("data"+File.separator+"EventsBag.ser");
			ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
			objectOutStream.writeObject(EventsBag.events);
			objectOutStream.flush();
			objectOutStream.close();
			fileOutStream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void load() throws ClassNotFoundException, FileNotFoundException{
		try {
			FileInputStream fileInStream = new FileInputStream("data"+File.separator+"EventsBag.ser");
			ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
			EventsBag.events = (ArrayList<Event>) objectInStream.readObject();
			objectInStream.close();
			fileInStream.close();
		} catch(IOException ioe) {
//			ioe.printStackTrace();	
		}
	}

}
