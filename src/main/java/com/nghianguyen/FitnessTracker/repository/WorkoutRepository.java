package com.nghianguyen.FitnessTracker.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer>
{
	@Query("SELECT w FROM Workout w WHERE w.user.email = :email")
	Collection<Workout> findWorkoutsByUser(String email);
}