/*
 *
 */
package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.User;

/**
 * User Data Access Object provides an abstract interface to some type of database or other persistence mechanism. It maps
 * application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the
 * database. This isolation supports the single responsibility principle. It separates what data access the application needs, in
 * terms of domain-specific objects and data types (the public interface of the DAO), from how these needs can be satisfied with a
 * specific DBMS, database schema, etc. (the implementation of the DAO).
 */
public interface UserDAO {

	/**
	 * Persists a new User in the data source.
	 *
	 * @param user
	 *            the user
	 */
	void addUser(User user);

	/**
	 * Optionally removes the User from the data source by id if found.
	 *
	 * @param userID
	 *            the user ID
	 */
	void deleteUser(Long userID);

	/**
	 * Optionally retrieves a Skill from the data source by email if it exists.
	 *
	 * @param email
	 *            the email
	 * @return the user
	 */
	User findUserByEmail(String email);

	/**
	 * Optionally retrieves a User from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the user
	 */
	User findUserByID(long id);

	/**
	 * Retrieves all Users from the data source.
	 *
	 * @return the list
	 */
	List<User> listUsers();

	/**
	 * Updates an existing User in the data source.
	 *
	 * @param user
	 *            the user
	 */
	void updateUser(User user);

}
