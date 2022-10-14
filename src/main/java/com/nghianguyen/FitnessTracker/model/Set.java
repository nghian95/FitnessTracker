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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * Set is annotated with @Entity to create the entity in the database via Hibernate.
 * Includes @ManyToMany relationship with Activity.
 */
@Entity
@Table(
	name = "activitySet", //has to be activitySet has the Set keyword has issues in SQL
	uniqueConstraints= 
		@UniqueConstraint(columnNames= {"reps","weight","setOrder"})
)
public class Set implements Comparable<Set>{
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int setID;
	
	@Column
	private int setOrder;
	
	@Column
	private int reps;
	
	@Column
	private int weight;
	
	@ManyToMany(mappedBy = "sets")
	@JsonIgnore
	private List<Activity> activity;
	
	public Set() {
		setOrder = 0;
		reps = 0;
		weight = 0;
		activity = new ArrayList<>();
	}
	
	public Set(int reps, int weight) {
		this();
		this.reps = reps;
		this.weight = weight;
	}

	public Set(int setOrder, int reps, int weight) {
		this(reps, weight);
		this.setOrder = setOrder;
	}
	
	public int getSetOrder() {
		return setOrder;
	}

	public void setSetOrder(int setOrder) {
		this.setOrder = setOrder;
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
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Set [setID=" + setID + ", reps=" + reps + ", weight=" + weight + ", activity=" + activity + "]";
	}

	@Override
	public int compareTo(Set s) {
		// TODO Auto-generated method stub
		return this.getSetOrder() - s.getSetOrder(); 
	}
	
	
}
