package com.nghianguyen.FitnessTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.Set;

import java.util.List;
import java.util.Optional;
/*
 * Provides the basic CRUD methods via JPARepository for Set
 */
@Repository
public interface SetRepository extends JpaRepository<Set, Integer>
{
	@Query("SELECT s.setID FROM Set s WHERE s.setOrder = :setOrder AND s.reps = :reps AND s.weight = :weight")
	Integer getSetIDByProperties(int setOrder, int reps, int weight);
}
