package com.nghianguyen.fitnesstracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nghianguyen.fitnesstracker.model.ActivityList;

/*
 * Provides the basic CRUD methods via JPARepository for ActivityList
 */
@Repository
public interface ActivityListRepository extends JpaRepository<ActivityList, Integer>
{
	/*
	 * Selects from the available list of activities and limits it based on the parameters passed.
	 * offSet will skip the previous numbers up to the offSet. pageSize is the # of results retrieved.
	 */
	@Query(value = "SELECT * FROM activity_list LIMIT :offSet, :pageSize",
			nativeQuery = true)
	List<ActivityList> getPagedActivityLists(int offSet, int pageSize);
}
