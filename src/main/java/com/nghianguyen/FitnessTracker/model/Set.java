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

@Entity
@Table(name = "activitySet")
public class Set {
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int setID;
	
	@Column
	private int reps;
	
	@Column
	private int weight;
	
	@ManyToMany(mappedBy = "sets")
	@JsonIgnore
	private List<Activity> activity;
	
	public Set() {
		reps = 0;
		weight = 0;
		activity = new ArrayList<>();
	}
	
	public Set(int reps, int weight) {
		this.reps = reps;
		this.weight = weight;
		activity = new ArrayList<>();
	}

	public int getSetID() {
		return setID;
	}

	public void setSetID(int setID) {
		this.setID = setID;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public List<Activity> getActivity() {
		return activity;
	}

	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}

	public void addActivity(Activity activity) {
		this.activity.add(activity);
	}
	
	@Override
	public String toString() {
		return "Set [setID=" + setID + ", reps=" + reps + ", weight=" + weight + ", activity=" + activity + "]";
	}
	
	
}
