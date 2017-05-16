package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable{

	private String title;
	private String salary;
	private String name;
	private Address address;
	private String ssn;
	private String phoneNumber;
	private LocalDate dateOfBirth;
	private String email;
	private String isEmployed;
	

	
	
	public Employee(String title, String salary, String name, String ssn, String phoneNumber,
			LocalDate dateOfBirth, String email, String isEmployed) {
		super();
		this.title = title;
		this.salary = salary;
		this.name = name;
		this.address = address;
		this.ssn = ssn;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.isEmployed = isEmployed;
		
	}

	
//	public String isTemp() {
//		return isTemp;
//	}

//	public void setTemp(String isTemp) {
//		this.isTemp = isTemp;
//	}

	public String isEmployed() {
		return isEmployed;
	}

	public void setEmployed(String isEmployed) {
		this.isEmployed = isEmployed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
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
