package com.nghianguyen.fitnesstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghianguyen.fitnesstracker.model.Location;

/*
 * Provides the basic CRUD methods via JPARepository for Location
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>
{
}
