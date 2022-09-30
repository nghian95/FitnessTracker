package com.nghianguyen.FitnessTracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "activity")
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
    private List<Set> sets;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Activity [activityID=" + activityID + ", activityList=" + activityList + ", sets=" + sets + ", comment="
				+ comment + "]";
	}


}
