package com.nghianguyen.FitnessTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>
// Tutorial → Entity type and Long --> primary key
{
//	List<Activity> findByPublished(boolean published);
}

