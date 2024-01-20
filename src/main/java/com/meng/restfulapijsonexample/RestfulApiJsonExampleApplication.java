package com.meng.restfulapijsonexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * create a microservice which serves the contents of user.json through a REST API.
 *
 * The service should expose three REST endpoints:
 *
 * GET /api/users - returns the list of all users.
 * GET /api/users/{userID} - returns a single user by ID.
 * GET /api/users/event/{eventType} - returns a list of users for the specified event type.
 * Examples of event_types:
 *
 * user_uuid
 * birthday
 * gender
 * phone
 *
 * event_type
 * event_info
 *
 * The above APIs should only return high-level characteristics of the user data. For example - name, contact, avatar, event_types etc.
 *
 * Docker image
 */
@SpringBootApplication
public class RestfulApiJsonExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApiJsonExampleApplication.class, args);
	}

}
