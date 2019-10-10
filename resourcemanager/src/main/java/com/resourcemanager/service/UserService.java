/*
 *
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.User;

/**
 * Provides an abstract interface for a single service layer for Users. The services do all the logic and data manipulation on the
 * data model and pass the updated model to the persistence layer.
 */
public interface UserService {

	/**
	 * Persists a new User in the data source.
	 *
	 * @param user
	 *            the user
	 */
	@Secured("ROLE_ADMIN")
	public void addUser(User user);

	/**
	 * Optionally removes the User from the data source by id if found.
	 *
	 * @param userID
	 *            the user ID
	 */
	@Secured("ROLE_ADMIN")
	public void deleteUser(Long userID);

	/**
	 * Optionally retrieves a Skill from the data source by email if it exists.
	 *
	 * @param userID
	 *            the user ID
	 * @return the user by ID
	 */
	public User getUserByID(Long userID);

	/**
	 * Optionally retrieves a Skill from the data source by email if it exists.
	 *
	 * @param userName
	 *            the user name
	 * @return the user by user name
	 */
	public User getUserByUserName(String userName);

	/**
	 * Retrieves all Users from the data source.
	 *
	 * @return the list
	 */
	@Secured("ROLE_ADMIN")
	public List<User> listUsers();

	/**
	 * Updates an existing User in the data source.
	 *
	 * @param user
	 *            the user
	 */
	@Secured("ROLE_USER")
	public void updateUser(User user);

}
