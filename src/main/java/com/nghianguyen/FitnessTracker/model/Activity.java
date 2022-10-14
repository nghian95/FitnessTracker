package com.nghianguyen.FitnessTracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*
 * Activity class with @Entity to create it in the database through Hibernate. 
 * Includes relationships with other entities ActivityList, Set, and Workout.
 */
@Entity
@Table(name = "activity")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sets"})
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int activityID;

    @ManyToOne
    private ActivityList activityList;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "activity_set_join",
		joinColumns = @JoinColumn(name="activityID"),
		inverseJoinColumns = @JoinColumn(name="setID")
	)
	@JsonIgnore
    private List<Set> sets;
	
	@NotNull
	@ManyToOne
	private Workout workout;

//    @Column(name = "reps")
//    private List<Integer> reps;
//
//    @Column(name = "weights")
//    private List<Integer> weights;
    
    @Column(name = "comment") 
    @Lob
    private String comment;
    
    public Activity() {
    	this.comment = "";
    	activityList = null;
        sets = new ArrayList<Set>();
// 	   	for (int i = sets.size(); i < 10; i++) {
// 	   		Set set = new Set();
// 	   		sets.add(set);
// 	   	}
        workout = new Workout();
    }

    public Activity(String comment) {
       this();
       this.comment = comment;
    }
    
    public Activity(ActivityList activityList) {
    	this();
        this.activityList = activityList;
    }
    
    public Activity(ActivityList activityList, String comment) {
    	this();
        this.activityList = activityList;
        this.comment = comment;
    }

    public Activity(ActivityList activityList, String comment, Workout workout) {
    	this();
        this.activityList = activityList;
        this.comment = comment;
        this.workout = workout;
    }
    
    public Activity(ActivityList activityList, String comment, Workout workout, Set set) {
    	this();
        this.activityList = activityList;
        this.comment = comment;
//        sets = new ArrayList<Set>();
//        sets.add(set);
        sets.set(0, set);
        this.workout = workout;
    }
    
    public Activity(ActivityList activityList, String comment, Workout workout, List<Set> sets) {
        this.activityList = activityList;
        this.comment = comment;
        this.sets = sets;
        this.workout = workout;
    }
    
	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public ActivityList getActivityList() {
		return activityList;
	}

	public void setActivityList(ActivityList activityList) {
		this.activityList = activityList;
	}

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public void addSet(Set set) {
		sets.add(set);
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	@Override
	public String toString() {
		return "Activity [activityID=" + activityID + ", activityList=" + activityList + ", sets=" + sets.size() + ", comment="
				+ comment + "]";
	}


}
