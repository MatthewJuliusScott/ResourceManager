/*
 * 
 */
package com.resourcemanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Resource;

/**
 * The Interface ResourceService.
 */
public interface ResourceService {

	/**
	 * Adds the resource.
	 *
	 * @param p the p
	 */
	@Secured("ROLE_ADMIN")
	public void addResource(Resource p);

	/**
	 * Delete resource.
	 *
	 * @param id the id
	 */
	@Secured("ROLE_ADMIN")
	public void deleteResource(Long id);

	/**
	 * Gets the resource by ID.
	 *
	 * @param id the id
	 * @return the resource by ID
	 */
	@Secured("ROLE_ADMIN")
	public Resource getResourceByID(Long id);

	/**
	 * List resources.
	 *
	 * @return the list
	 */
	@Secured("ROLE_ADMIN")
	public List<Resource> listResources();

	/**
	 * Search resources.
	 *
	 * @param skillId the skill id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param hours the hours
	 * @return the list
	 */
	@Secured("ROLE_ADMIN")
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours);

	/**
	 * Update resource.
	 *
	 * @param p the p
	 */
	@Secured("ROLE_ADMIN")
	public void updateResource(Resource p);

}
