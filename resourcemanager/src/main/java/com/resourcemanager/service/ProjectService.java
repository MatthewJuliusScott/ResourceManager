/*
 * 
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Project;

/**
 * The Interface ProjectService.
 */
public interface ProjectService {

	/**
	 * Adds the project.
	 *
	 * @param project the project
	 */
	@Secured("ROLE_ADMIN")
	public void addProject(Project project);

	/**
	 * Delete project.
	 *
	 * @param id the id
	 */
	@Secured("ROLE_ADMIN")
	public void deleteProject(Project project);

	/**
	 * Gets the project by ID.
	 *
	 * @param id the id
	 * @return the project by ID
	 */
	@Secured("ROLE_USER")
	public Project getProjectById(Long id);

	/**
	 * List projects.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Project> listProjects();

	/**
	 * Update project.
	 *
	 * @param project the project
	 */
	@Secured("ROLE_USER")
	public void updateProject(Project project);
	

}
