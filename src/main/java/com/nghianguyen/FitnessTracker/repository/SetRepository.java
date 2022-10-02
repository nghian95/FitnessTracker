package com.nghianguyen.FitnessTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.Set;

import java.util.List;
import java.util.Optional;

@Repository
public interface SetRepository extends JpaRepository<Set, Integer>
// Tutorial → Entity type and Long --> primary key
{
//	List<Activity> findByPublished(boolean published);
}
