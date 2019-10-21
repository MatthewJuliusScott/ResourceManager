/*
 *
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Project;

/**
 * Provides an abstract interface for a single service layer for Projects. The services do all the logic and data manipulation on
 * the data model and pass the updated model to the persistence layer.
 */
public interface ProjectService {

	/**
	 * Persists a new Project in the data source.
	 *
	 * @param project
	 *            the project
	 */
	@Secured("ROLE_ADMIN")
	public void addProject(Project project);

	/**
	 * Removes the Project from the data source.
	 *
	 * @param id
	 *            the id
	 */
	@Secured("ROLE_ADMIN")
	public void deleteProject(Project project);

	/**
	 * Optionally retrieves a Project from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the project by ID
	 */
	@Secured("ROLE_USER")
	public Project getProjectById(Long id);

	/**
	 * Retrieves all Projects from the data source.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Project> listProjects();

	/**
	 * Updates an existing Project in the data source.
	 *
	 * @param project
	 *            the project
	 */
	@Secured("ROLE_USER")
	public void updateProject(Project project);

}
