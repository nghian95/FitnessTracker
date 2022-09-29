package com.nghianguyen.FitnessTracker.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	private List<Activity> activity;
	
	public Set() {
		reps = 0;
		weight = 0;
	}
	
	public Set(int reps, int weight) {
		this.reps = reps;
		this.weight = weight;
	}
}
