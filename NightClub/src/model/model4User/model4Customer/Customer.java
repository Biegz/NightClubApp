package model.model4User.model4Customer;

import java.io.Serializable;
import java.util.ArrayList;

import model.Event;
import model.Table;
import model.Ticket;
import model.model4Address.Address;
import model.model4User.User;

public class Customer extends User{

	private String firstName;
	private String lastName;
	private String favGenre;
	private int age;
	private String gender;
	private ArrayList<Ticket> tickets;
	private ArrayList<Table> tables;
	private double balance;

	public Customer(String username, double passwordHash) {
		super(username, passwordHash);
		tickets = new ArrayList<Ticket>();
		tables = new ArrayList<Table>();
		
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

	public String getFavGenre() {
		return favGenre;
	}

	public void setFavGenre(String favGenre) {
		this.favGenre = favGenre;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void addTicket(Ticket ticket){
		tickets.add(ticket);
	}

	public void removeTicket(Ticket ticket){
		tickets.remove(ticket);
	}

	public void addTable(Table table) {
		tables.add(table);
	}

	public void removeTable(Table table) {
		tables.remove(table);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addBalance(double num){
		this.balance += num;
	}

	public void subtractBalance(double num){
		this.balance -= num;
	}

	public Ticket findTicket(Event event){
		Ticket temp = null;
		for(Ticket t : tickets){
			if(t.getEvent().equals(event)){
				temp = t;
			}
		}
		return temp;
	}

	public Table findTable(Event event){
		Table temp = null;
		for (Table t: tables) {
			if(t.getEvent().equals(event)) {
				temp = t;
			}
		}
		return temp;
	}

	@Override
	public String display(){
		String display = null;
		display = "Username: " + getUsername()
				+ ". Name: " + firstName + " " + lastName + ". " + gender + ". " + age +
				" years old. ";
		return display;
	}

}
