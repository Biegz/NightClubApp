package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Business extends User {

	private String name;
	private ArrayList<Event> events;
	private ArrayList<Employee> employees;
	private FinancialInfo financeInfo;

	public Business(String username, double passwordHash) {
		super(username, passwordHash);
		financeInfo = new FinancialInfo();
	}

	public void addEmployee(Employee employee) {
		if (employees != null) {
			employees.add(employee);
		} else {
			employees = new ArrayList<>();
			employees.add(employee);
		}
	}
	
	

	public void removeEmployee(String name) {
		Employee delete = null;
		for (Employee e : employees) {
			if (e.getName().equals(name)) {
				delete = e;
			}
		}
		employees.remove(delete);
	}

	public StringBuilder displayEmployees() {
		StringBuilder allEmployees = null;
		for (Employee e : employees) {
			allEmployees.append(e.toString());
		}
		return allEmployees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addEvent(Event event) {
		events.add(event);
	}

	public void removeEvent(Event event) {
		events.remove(event);
	}

	public ArrayList<Event> getEventList() {
		return events;
	}

	public FinancialInfo getFinanceInfo() {
		return this.financeInfo;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	@Override
	public String display() {
		String display = null;
		display = "Username: " + getUsername() + ". Name: " + name + ". ";
		return display;
	}

}
