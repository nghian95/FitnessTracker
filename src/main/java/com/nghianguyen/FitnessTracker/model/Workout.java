package com.nghianguyen.FitnessTracker.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int workoutID;
    
    @Column
    private Date workoutDate;
    
    @ManyToOne
    private Location location;
    
    public Workout() {
    	workoutDate = null;
    	location = null;
    }

	public Workout(Date workoutDate, Location location) {
		this.workoutDate = workoutDate;
		this.location = location;
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
