package model.model4User;

import java.io.Serializable;

import model.model4Address.Address;

public abstract class User implements Serializable {

	private String username;
	private double passwordHash;
	private String email;
	private String phoneNum;
	private Address address;

	public User(String username, double passwordHash) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(double passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String display() {
		String toString = null;
		toString = "Username: " + username + ". Password Hash: " + passwordHash;
		return toString;
	}

}
