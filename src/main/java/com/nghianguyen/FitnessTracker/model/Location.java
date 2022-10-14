package com.nghianguyen.FitnessTracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/*
 * Location class that uses @Entity to generate the database table via Hibernate.
 * Has @ManyToMany relationship with User and @Column properties
 */
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int locationID;
    
    @Column(length=50)
    private String locationName;
    
    @Column(length=100)
    private String locationAddress;

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private List<User> user;
    
    public Location() {
    	locationName = "";
    	locationAddress = "";
    	user = new ArrayList<>();
    }
    
	public Location(String locationName, String locationAddress) {
		this.locationName = locationName;
		this.locationAddress = locationAddress;
		user = new ArrayList<>();
	}
	
	public Location(String locationName, String locationAddress, List<User> user) {
		this.locationName = locationName;
		this.locationAddress = locationAddress;
		this.user = user;
	}
	
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	@Override
	public String toString() {
		return locationName + " | " + locationAddress;
	}
    
	
    
}
