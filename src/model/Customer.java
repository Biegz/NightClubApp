package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class Customer extends User {

	private String firstName;
	private String lastName;
	private Genre favGenre;
	private long age;
	private String gender;
	private ArrayList<Ticket> tickets;
	private ArrayList<Table> tables;
	private ArrayList<Event> myEvents;
	private double balance;
	private Pane recommendation;
	private LocalDate birthday;

	public Customer(String username, double passwordHash) {
		super(username, passwordHash);
		tickets = new ArrayList<Ticket>();
		tables = new ArrayList<Table>();
		myEvents = new ArrayList<Event>();
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		age = (LocalDate.now().minusYears(birthday.getYear()	).getYear()	);
	}

	public Pane getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Pane recommendation) {
		this.recommendation = recommendation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Genre getFavGenre() {
		return favGenre;
	}

	public void setFavGenre(Genre favGenre) {
		this.favGenre = favGenre;
	}

	public long getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	public void removeTicket(Ticket ticket) {
		tickets.remove(ticket);
	}

	public void addTable(Table table) {
		tables.add(table);
	}

	public void removeTable(Table table) {
		tables.remove(table);
	}

	public void removeEvent(Event event) {
		myEvents.remove(event);
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addBalance(double num) {
		this.balance += num;
	}

	public void subtractBalance(double num) {
		this.balance -= num;
	}

	public Ticket findTicket(Event event) {
		Ticket temp = null;
		for (Ticket t : tickets) {
			if (t.getEvent().equals(event)) {
				temp = t;
			}
		}
		return temp;
	}

	public Table findTable(Event event) {
		Table temp = null;
		for (Table t : tables) {
			if (t.getEvent().equals(event)) {
				temp = t;
			}
		}
		return temp;
	}

	
	public boolean findEvent(String name) {
		for (Event e: myEvents) {
			if (e.getEventName().equalsIgnoreCase(name));
			return true;
		}
		return false;
	}
	
	public void setEventList(Event e) {
		for (Event event: myEvents) {
			if (event.getEventName().equalsIgnoreCase(e.getEventName())) {
				return;
			}
		}
		myEvents.add(e);
	}
	
	public ArrayList<Event> getEventList() {
		return myEvents;	
	}

	public ArrayList<Ticket> getTicketList() {
		return tickets;
	}
	
	public ArrayList<Table> getTableList() {
		return tables;
	}
	
	@Override
	public String display() {
		String display = null;
		display = "Username: " + getUsername() + ". Name: " + firstName + " " + lastName + ". " + gender + ". " + age
				+ " years old. ";
		return display;
	}
	


}
