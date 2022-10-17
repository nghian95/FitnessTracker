package com.nghianguyen.fitnesstracker.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * @Entity used to generate the related Entity in database via Hibernate. 
 * Includes @ManyToMany relationship with Location and Role.
 */
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
	
	@JoinTable(
			name = "user_location_join",
			joinColumns = @JoinColumn(name="email"),
			inverseJoinColumns = @JoinColumn(name="locationID")
	)
	@Column
	@ManyToMany
	@JsonIgnore
	private Set<Location> locations;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	   @JoinTable(
	           name = "users_roles",
	           joinColumns = @JoinColumn(
	                   name = "user_email", referencedColumnName = "email"),
	           inverseJoinColumns = @JoinColumn(
	                   name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	public User() {
		password = "";
		firstName = "";
		lastName = "";
		phoneNumber = 0;
		locations = new HashSet<>();
		roles = null;
	}

	public User(String password, String firstName, String lastName, int phoneNumber) {
		this();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public User(String password, String firstName, String lastName, int phoneNumber, Collection<Role> roles) {
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
	}
	
	
	
//	public List<Location> getLocations() {
	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
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
	
	public Collection<Role> getRoles() {
       return roles;
	}

	public void setRoles(Collection<Role> roles) {
       this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [email=" + email + 
				", password=" + "*********" + 
				", firstName=" + firstName + 
				", lastName=" + lastName + 
				", phoneNumber=" + phoneNumber + "]" +
				", locations=" + locations +
                ", roles=" + roles +
                '}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, locations, password, phoneNumber, roles);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(locations, other.locations)
				&& Objects.equals(password, other.password) && phoneNumber == other.phoneNumber
				&& Objects.equals(roles, other.roles);
	}
	
	
	
}
