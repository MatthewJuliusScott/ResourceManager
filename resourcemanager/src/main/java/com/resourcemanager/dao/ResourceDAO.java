/*
 *
 */
package com.resourcemanager.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resourcemanager.model.Resource;

/**
 * Resource Data Access Object provides an abstract interface to some type of database or other persistence mechanism. It maps
 * application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the
 * database. This isolation supports the single responsibility principle. It separates what data access the application needs, in
 * terms of domain-specific objects and data types (the public interface of the DAO), from how these needs can be satisfied with a
 * specific DBMS, database schema, etc. (the implementation of the DAO).
 */
@Repository
public interface ResourceDAO {

	/**
	 * Persists a new Resource in the data source.
	 *
	 * @param resource
	 *            the resource
	 */
	public void addResource(Resource resource);

	/**
	 * Optionally retrieves a Resource from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the resource by ID
	 */
	public Resource getResourceByID(Long id);

	/**
	 * Retrieves all Resources from the data source.
	 *
	 * @return the list
	 */
	public List<Resource> listResources();

	/**
	 * Removes the Resource from the data source.
	 *
	 * @param id
	 *            the id
	 */
	public void removeResource(Long id);

	/**
	 * Retrieves all Resources from the data source that match the search criteria of Skill, hours and no allocations during the
	 * specified time period from the persistence layer and passes them to the Resources view to display them to the user and
	 * respond to their input.
	 *
	 * @param skillId
	 *            the skill id
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param hours
	 *            the hours
	 * @return the list
	 */
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours);

	/**
	 * Updates an existing Resource in the data source.
	 *
	 * @param resource
	 *            the resource
	 */
	public void updateResource(Resource resource);
}
