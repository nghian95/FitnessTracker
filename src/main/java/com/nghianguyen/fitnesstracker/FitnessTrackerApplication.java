package com.nghianguyen.fitnesstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
// Beginning of the application. 
import org.springframework.web.bind.annotation.CrossOrigin;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class FitnessTrackerApplication {

	
	public static void main(String[] args) {

		SpringApplication.run(FitnessTrackerApplication.class, args);

	}

}
