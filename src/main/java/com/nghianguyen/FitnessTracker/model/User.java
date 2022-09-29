package com.nghianguyen.FitnessTracker.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name="email")
	private String email;
	
	@Column(nullable=false, length = 75, name="password")
	private String password;
	
	@Column(nullable = false, length = 50, name="firstName")
	private String firstName;
	
	@Column(nullable = false, length = 50, name="lastName")
	private String lastName;
	
	@Column
	private int phoneNumber;
	
	public User() {
		password = "";
		firstName = "";
		lastName = "";
		phoneNumber = 0;
	}

	public User(String password, String firstName, String lastName, int phoneNumber) {
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	
	
}
