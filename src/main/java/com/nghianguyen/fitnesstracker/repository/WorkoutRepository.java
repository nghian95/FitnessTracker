package com.nghianguyen.fitnesstracker.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.fitnesstracker.model.Workout;
/*
 * Provides the basic CRUD methods via JPARepository for Workout.
 * Includes custom query to select all workouts that are related to specific email
 */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer>
{
	@Query("SELECT w FROM Workout w WHERE w.user.email = :email")
	List<Workout> findWorkoutsByUser(String email);
}