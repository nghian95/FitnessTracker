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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "activity")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sets"})
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int activityID;

    @ManyToOne
    private ActivityList activityList;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "activity_set_join",
		joinColumns = @JoinColumn(name="activityID"),
		inverseJoinColumns = @JoinColumn(name="setID")
	)
	@JsonIgnore
    private List<Set> sets;
	
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
    }

    public Activity(String comment) {
        this.activityList = null;
        this.comment = comment;
        sets = new ArrayList<Set>();
    }
    
    public Activity(ActivityList activityList) {
        this.activityList = activityList;
        this.comment = "";
        sets = new ArrayList<Set>();
    }
    
    public Activity(ActivityList activityList, String comment) {
        this.activityList = activityList;
        this.comment = comment;
        sets = new ArrayList<Set>();
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
