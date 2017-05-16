package controller;

import java.util.EventObject;

import model.Employee;


public class EmployeeEvent extends EventObject {
	private Employee employee;
	
	public EmployeeEvent(Object source) {
		super(source);
	}
	
	public EmployeeEvent(Object source, Employee employee) {
		super(source);
		this.employee = employee;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	

}
