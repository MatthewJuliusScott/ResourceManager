/*
 *
 */
package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Project;

/**
 * Project Data Access Object provides an abstract interface to some type of database or other persistence mechanism. It maps
 * application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the
 * database. This isolation supports the single responsibility principle. It separates what data access the application needs, in
 * terms of domain-specific objects and data types (the public interface of the DAO), from how these needs can be satisfied with a
 * specific DBMS, database schema, etc. (the implementation of the DAO).
 */
public interface ProjectDAO {

	/**
	 * Persists a new Project in the data source.
	 *
	 * @param project
	 *            the project
	 */
	public void addProject(Project project);

	/**
	 * Optionally retrieves a Project from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the project by ID
	 */
	public Project getProjectByID(Long id);

	/**
	 * Retrieves all Projects from the data source.
	 *
	 * @return the list
	 */
	public List<Project> listProjects();

	/**
	 * Removes the Project from the data source.
	 *
	 * @param id
	 *            the id
	 */
	public void removeProject(Project project);

	/**
	 * Updates an existing Project in the data source.
	 *
	 * @param project
	 *            the project
	 */
	public void updateProject(Project project);

}
