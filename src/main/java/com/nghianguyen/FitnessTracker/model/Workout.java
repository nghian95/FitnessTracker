package com.nghianguyen.FitnessTracker.model;

//import java.util.Date;
import java.sql.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int workoutID;
    
    @Column
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date workoutDate;
    
    @ManyToOne
    private Location location;
    
    @ManyToOne
    private User user;
    
    public Workout() {
    	
    	workoutDate = new Date(System.currentTimeMillis());
    	location = null;
    	user = null;
    }
    
    public Workout(User user) {
    	workoutDate = new Date(System.currentTimeMillis());
    	location = null;
    	this.user = user;
    }
    
    public Workout(Date workoutDate, User user) {
    	this.workoutDate = workoutDate;
    	location = null;
    	this.user = user;
    }

	public Workout(Date workoutDate, User user, Location location) {
		this.workoutDate = workoutDate;
		this.user = user;
		this.location = location;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getWorkoutID() {
		return workoutID;
	}

	public void setWorkoutID(int workoutID) {
		this.workoutID = workoutID;
	}

	public Date getWorkoutDate() {
		return workoutDate;
	}

	public void setWorkoutDate(Date workoutDate) {
		this.workoutDate = workoutDate;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    
    
}
