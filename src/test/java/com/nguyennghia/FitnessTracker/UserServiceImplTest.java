package com.nguyennghia.FitnessTracker;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.nghianguyen.fitnesstracker.FitnessTrackerApplication;
import com.nghianguyen.fitnesstracker.model.Location;
import com.nghianguyen.fitnesstracker.model.User;
import com.nghianguyen.fitnesstracker.service.LocationService;
import com.nghianguyen.fitnesstracker.service.UserServiceImpl;

/*
 * Tests if UserService methods work
 */
@SpringBootTest(classes = FitnessTrackerApplication.class)
class UserServiceImplTest {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	LocationService locationService;
	
	/*
	 * Tests three methods, findByEmail(), updateUser(), and deleteUserByEmail() for UserService class.
	 */
	@Test
	@Transactional
	void testFindByEmailUpdateUserDeleteUserByEmail() {
		Location location = locationService.getLocationByID(8).get();
		User user = new User("test@asdf.com","password", "John", "Doe", 1234567899, location);
		userServiceImpl.addUser(user);
		User retrievedUser = userServiceImpl.findByEmail(user.getEmail()).get();
		Assertions.assertEquals(user, retrievedUser);
		user.setFirstName("Jane");
		userServiceImpl.updateUser(user.getEmail(), user);
		retrievedUser = userServiceImpl.findByEmail(user.getEmail()).get();
		Assertions.assertEquals(user, retrievedUser);
		userServiceImpl.deleteUserByEmail(user.getEmail());
		Optional<User> retrievedUserData = userServiceImpl.findByEmail(user.getEmail());
		Assertions.assertTrue(retrievedUserData.isEmpty());
	}

}
