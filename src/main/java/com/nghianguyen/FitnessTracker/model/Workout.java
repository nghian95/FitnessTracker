package com.nghianguyen.FitnessTracker.model;

//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/*
 * Workout class that uses @Entity to create the database table via Hibernate.
 * @ManyToOne relationships with Location and User. @OneToMany relationship with Activity.
 */
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
    
    @NotNull
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy="workout", cascade = CascadeType.ALL)
    private List<Activity> activities;
    
    public Workout() {
    	workoutDate = new Date(System.currentTimeMillis());
    	location = null;
    	user = null;
    	activities = new ArrayList<>();
    }
    
    public Workout(User user) {
    	workoutDate = new Date(System.currentTimeMillis());
    	location = null;
    	this.user = user;
    	activities = new ArrayList<>();
    }
    
    public Workout(Date workoutDate, User user) {
    	this.workoutDate = workoutDate;
    	location = null;
    	this.user = user;
    	activities = new ArrayList<>();
    }

	public Workout(Date workoutDate, User user, Location location) {
		this.workoutDate = workoutDate;
		this.user = user;
		this.location = location;
		activities = new ArrayList<>();
	}
	
	public Workout(Date workoutDate, User user, Location location, List<Activity> activities) {
		this.workoutDate = workoutDate;
		this.user = user;
		this.location = location;
		this.activities = activities;
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
