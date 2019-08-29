/*
 * 
 */
package com.resourcemanager.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resourcemanager.model.Resource;

/**
 * The Interface ResourceDAO.
 */
@Repository
public interface ResourceDAO {

	/**
	 * Adds the resource.
	 *
	 * @param p the p
	 */
	public void addResource(Resource p);

	/**
	 * Gets the resource by ID.
	 *
	 * @param id the id
	 * @return the resource by ID
	 */
	public Resource getResourceByID(Long id);

	/**
	 * List resources.
	 *
	 * @return the list
	 */
	public List<Resource> listResources();

	/**
	 * Removes the resource.
	 *
	 * @param id the id
	 */
	public void removeResource(Long id);

	/**
	 * Search resources.
	 *
	 * @param skillId the skill id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param hours the hours
	 * @return the list
	 */
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours);

	/**
	 * Update resource.
	 *
	 * @param p the p
	 */
	public void updateResource(Resource p);
}
