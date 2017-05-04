package model;

import java.io.Serializable;

import model.model4Address.Address;

public class Employee implements Serializable{

	private String title;
	private double salary;
	private String name;
	private Address address =  new Address();
	private String ssn;
	private String phoneNumber;
	private String dateOfBirth;
	private String email;
	private boolean isEmployed;
	private boolean isTemp;

	public Employee() {
		isEmployed = true;
	}

	public boolean isTemp() {
		return isTemp;
	}

	public void setTemp(boolean isTemp) {
		this.isTemp = isTemp;
	}

	public boolean isEmployed() {
		return isEmployed;
	}

	public void setEmployed(boolean isEmployed) {
		this.isEmployed = isEmployed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double slaray) {
		this.salary = slaray;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Title: " + title + ", salary: " + salary + ", name: " + name + ", address: " + address.display()
				+ ", ssn: " + ssn + ", phoneNumber: " + phoneNumber + ", dateOfBirth: " + dateOfBirth + ", email: "
				+ email + " .\n";
	}

}
