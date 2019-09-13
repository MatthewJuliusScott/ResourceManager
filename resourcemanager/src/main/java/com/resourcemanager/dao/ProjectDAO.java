/*
 * 
 */
package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Project;

/**
 * The Interface ProjectDAO.
 */
public interface ProjectDAO {

	/**
	 * Adds the project.
	 *
	 * @param project the project
	 */
	public void addProject(Project project);

	/**
	 * Gets the project by ID.
	 *
	 * @param id the id
	 * @return the project by ID
	 */
	public Project getProjectByID(Long id);

	/**
	 * List projects.
	 *
	 * @return the list
	 */
	public List<Project> listProjects();

	/**
	 * Removes the project.
	 *
	 * @param id the id
	 */
	public void removeProject(Project project);

	/**
	 * Update project.
	 *
	 * @param project the project
	 */
	public void updateProject(Project project);
	
}
