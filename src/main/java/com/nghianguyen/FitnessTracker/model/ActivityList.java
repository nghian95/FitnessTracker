package com.nghianguyen.FitnessTracker.model;

import javax.persistence.*;
/*
 * ActivityList class that uses @Entity to generate the database tables via Hibernate.
 * Has column constraints added via annotations @Column
 */
@Entity
@Table(name = "activityList")
public class ActivityList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int activityListID;
    
    @Column(name="activityName", nullable=false, length=50)
    private String activityName;
    
    @Column(length = 500)
    @Lob
    private String description;
    
    @Column(length = 50)
    private String majorMuscleGroup;

    @Column(length = 100)
    private String minorMuscles;
    
    public ActivityList() {
    	activityName = "";
    	description = "";
    	majorMuscleGroup = "";
    	minorMuscles = "";
    }
    
    public ActivityList(String activityName, String description, String majorMuscleGroup, String minorMuscles) {
    	this.activityName = activityName;
    	this.description = description;
    	this.majorMuscleGroup = majorMuscleGroup;
    	this.minorMuscles = minorMuscles;
    }

	public int getActivityListID() {
		return activityListID;
	}

	public void setActivityListID(int activityListID) {
		this.activityListID = activityListID;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMajorMuscleGroup() {
		return majorMuscleGroup;
	}

	public void setMajorMuscleGroup(String majorMuscleGroup) {
		this.majorMuscleGroup = majorMuscleGroup;
	}

	public String getMinorMuscles() {
		return minorMuscles;
	}

	public void setMinorMuscles(String minorMuscles) {
		this.minorMuscles = minorMuscles;
	}

	@Override
	public String toString() {
		return "ActivityList [activityListID=" + activityListID + ", activityName=" + activityName + ", description="
				+ description + ", majorMuscleGroup=" + majorMuscleGroup + ", minorMuscles=" + minorMuscles + "]";
	}
    
    
}
