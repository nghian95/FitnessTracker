package com.nghianguyen.fitnesstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghianguyen.fitnesstracker.model.ActivityList;

/*
 * Provides the basic CRUD methods via JPARepository for ActivityList
 */
@Repository
public interface ActivityListRepository extends JpaRepository<ActivityList, Integer>
{
}
