package com.nghianguyen.fitnesstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghianguyen.fitnesstracker.model.User;
/*
 * Provides the basic CRUD methods via JPARepository for User
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>
{
//	User findByEmail(String email);
}