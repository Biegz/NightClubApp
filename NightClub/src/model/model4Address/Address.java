package model.model4Address;

import java.io.Serializable;

public class Address implements Serializable{

	private String street;
	private String number;
	private String zipcode;
	private String state;
	private String city;

	public Address(){
		
	}
	
	

	public Address(String street, String number, String zipcode, String state, String city) {
		super();
		this.street = street;
		this.number = number;
		this.zipcode = zipcode;
		this.state = state;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipcode() {

		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String display(){
		String display = null;
		display = number + " " + street + " " + city + ", " + state + " " + zipcode + ".";
		return display;
	}


}