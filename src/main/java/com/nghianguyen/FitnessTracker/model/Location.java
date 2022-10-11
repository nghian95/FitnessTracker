package com.nghianguyen.FitnessTracker.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToMany(mappedBy = "locations", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> user;
    
    public Location() {
    	locationName = "";
    	locationAddress = "";
    }
    
	public Location(String locationName, String locationAddress) {
		this.locationName = locationName;
		this.locationAddress = locationAddress;
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
