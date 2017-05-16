package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import controller.Current;

public class Event extends Observable implements Serializable {
	
	private Business business;
	private String eventName;
	private Genre genre;
	private String description;
	private Address address;
	private LocalDate date;
	private int totalTickets;
	private double ticketPrice;
	private int totalTables;
	private double tablePrice;
	private int ticketsAvailable;
	private int tablesAvailable;
	public ArrayList<Customer> customerList;

	public Event() {
		customerList = new ArrayList<Customer>();
		business = Current.getBusiness();
	}

	public Event(Business business, String eventName, Genre genre, String description, Address address, LocalDate date,
			int totalTickets, double ticketPrice, int totalTables, double tablePrice) {
		//super();
		customerList = new ArrayList<Customer>();
		business = Current.getBusiness();
		this.business = business;
		this.eventName = eventName;
		this.genre = genre;
		this.description = description;
		this.address = address;
		this.date = date;
		this.totalTickets = totalTickets;
		this.ticketPrice = ticketPrice;
		this.totalTables = totalTables;
		this.tablePrice = tablePrice;
		this.ticketsAvailable = totalTickets;
		this.tablesAvailable = totalTables;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

//	public void setCustomerList(ArrayList<Customer> customerList) {
//		this.customerList = customerList;
//	}

	public void setTicketsAvailable(int ticketsAvailable) {
		this.ticketsAvailable = ticketsAvailable;
		setChanged();
		notifyObservers();
	}

	public void setTablesAvailable(int tablesAvailable) {
		this.tablesAvailable = tablesAvailable;
		setChanged();
		notifyObservers();
	}

	public String getEventName() {
		return eventName;
	}
	
	public Business getBusiness() {
		return business;
	}
	
	public void setBusiness(Business business) {
		this.business = business;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}

	public int getTotalTables() {
		return totalTables;
	}

	public void setTotalTables(int totalSeats) {
		this.totalTables = totalSeats;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress(){
		return address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public double getTablePrice() {
		return tablePrice;
	}

	public void setTablePrice(double tablePrice) {
		this.tablePrice = tablePrice;
	}

	public void addCustomer(Customer customer) {
		this.customerList.add(customer);
		// Decrement tickets available here
		//notifyObservers();
	}

	public void removeCustomer(Customer customer) {
		customerList.remove(customer);
		notifyObservers();
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public int getTicketsAvailable() {
		return ticketsAvailable;
	}

	public int getTablesAvailable() {
		return tablesAvailable;
	}
	
	public ArrayList<Customer> getAttending(){
		return customerList;
	}
	
	public String getEventsBusiness(){
		return getBusiness().getName();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		return getEventsBusiness() + " is hosting" + " the " + genre + " event: " + eventName + ". This Event is on: " + date.getDayOfWeek() + ", in: " + address.getCity() + ". " + description;
	}

}
