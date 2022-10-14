package com.nghianguyen.FitnessTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.ActivityList;

import java.util.List;
import java.util.Optional;

/*
 * Provides the basic CRUD methods via JPARepository for ActivityList
 */
@Repository
public interface ActivityListRepository extends JpaRepository<ActivityList, Integer>
{
}
