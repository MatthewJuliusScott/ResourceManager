/*
 * 
 */
package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.User;

/**
 * The Interface UserDAO.
 */
public interface UserDAO {

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 */
	void addUser(User user);

	/**
	 * Delete user.
	 *
	 * @param userID the user ID
	 */
	void deleteUser(Long userID);

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	User findUserByEmail(String email);

	/**
	 * Find user by ID.
	 *
	 * @param id the id
	 * @return the user
	 */
	User findUserByID(long id);

	/**
	 * List users.
	 *
	 * @return the list
	 */
	List<User> listUsers();

	/**
	 * Update user.
	 *
	 * @param user the user
	 */
	void updateUser(User user);

}
