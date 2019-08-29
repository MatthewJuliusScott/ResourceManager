/*
 * 
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.User;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 */
	@Secured("ROLE_ADMIN")
	public void addUser(User user);

	/**
	 * Delete user.
	 *
	 * @param userID the user ID
	 */
	@Secured("ROLE_ADMIN")
	public void deleteUser(Long userID);

	/**
	 * Gets the user by ID.
	 *
	 * @param userID the user ID
	 * @return the user by ID
	 */
	public User getUserByID(Long userID);
	
	/**
	 * Gets the user by user name.
	 *
	 * @param userName the user name
	 * @return the user by user name
	 */
	public User getUserByUserName(String userName);

	/**
	 * List users.
	 *
	 * @return the list
	 */
	@Secured("ROLE_ADMIN")
	public List<User> listUsers();

	/**
	 * Update user.
	 *
	 * @param user the user
	 */
	@Secured("ROLE_USER")
	public void updateUser(User user);

}
