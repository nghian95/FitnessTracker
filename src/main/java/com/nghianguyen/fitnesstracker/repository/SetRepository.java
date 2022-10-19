package com.nghianguyen.fitnesstracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.fitnesstracker.model.Set;
/*
 * Provides the basic CRUD methods via JPARepository for Set
 */
@Repository
public interface SetRepository extends JpaRepository<Set, Integer>
{
	@Query("SELECT s.setID FROM Set s WHERE s.setOrder = :setOrder AND s.reps = :reps AND s.weight = :weight")
	Integer getSetIDByProperties(int setOrder, int reps, int weight);
}
