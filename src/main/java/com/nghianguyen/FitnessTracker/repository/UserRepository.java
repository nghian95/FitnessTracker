package com.nghianguyen.FitnessTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghianguyen.FitnessTracker.model.User;

import java.util.List;
import java.util.Optional;
/*
 * Provides the basic CRUD methods via JPARepository for User
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>
{
//	User findByEmail(String email);
}