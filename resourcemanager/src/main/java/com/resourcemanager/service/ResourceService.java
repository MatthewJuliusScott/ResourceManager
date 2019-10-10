/*
 *
 */
package com.resourcemanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Resource;

/**
 * Provides an abstract interface for a single service layer for Resources. The services do all the logic and data manipulation on
 * the data model and pass the updated model to the persistence layer.
 */
public interface ResourceService {

	/**
	 * Persists a new Resource in the data source.
	 *
	 * @param resource
	 *            the resource
	 */
	@Secured("ROLE_ADMIN")
	public void addResource(Resource resource);

	/**
	 * Removes the Resource from the data source.
	 *
	 * @param id
	 *            the id
	 */
	@Secured("ROLE_ADMIN")
	public void deleteResource(Long id);

	/**
	 * Optionally retrieves a Resource from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the resource by ID
	 */
	@Secured("ROLE_USER")
	public Resource getResourceByID(Long id);

	/**
	 * Retrieves all Resources from the data source.
	 *
	 * @return the list
	 */
	@Secured("ROLE_ADMIN")
	public List<Resource> listResources();

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
	@Secured("ROLE_ADMIN")
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours);

	/**
	 * Updates an existing Resource in the data source.
	 *
	 * @param resource
	 *            the resource
	 */
	@Secured("ROLE_ADMIN")
	public void updateResource(Resource resource);

}
