package view;

import java.time.LocalDate;

import controller.Current;
import controller.Pane4EventListener;
import controller.TicketButtonEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;


public class Pane4EmployeeView {

	//private PaneForAddress paneForAddress;
	private GridPane employeePane;
	private Label name;
	private Label title;
	private Label salary;
	private Label address;
	private Label ssn;
	private Label phoneNumber;
	private Label birthDate;
	private Label email;
	private Label status;
	

	public GridPane employeePane(){
		employeePane = new GridPane();
		employeePane.setHgap(10);
		employeePane.setVgap(10);
		employeePane.setPadding(new Insets(10,10,10,10));
		employeePane.addColumn(0, new Label(" Name "),new Label(" Title "),new Label(" Salary "),new Label(" SSN "),new Label(" Phone "), new Label(" eMail "),new Label(" Employment Status "));
		employeePane.addColumn(1, getName(), getTitle(), getSalary(), getSsn(), getPhoneNumber() , getEmail(), getStatus());
		return employeePane;
	}


	private Node getDob() {
		// TODO Auto-generated method stub
		return null;
	}


	public Label getName() {
		return name;
	}


	public void setName(String name) {
		this.name = new Label(name);
	}


	public Label getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = new Label(title);
	}


	public Label getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary  = new Label(salary);
	}


	public Label getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = new Label(address);
	}


	public Label getSsn() {
		return ssn;
	}


	public void setSsn(String ssn) {
		this.ssn  = new Label(ssn);
	}


	public Label getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber  = new Label(phoneNumber);
	}


	public Label getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate  = new Label(birthDate.toString());
	}


	public Label getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email  = new Label(email);
	}


	public Label getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = new Label(status);
	}

	
}