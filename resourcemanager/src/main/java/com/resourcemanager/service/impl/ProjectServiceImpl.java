/*
 * 
 */
package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.ProjectDAO;
import com.resourcemanager.model.Project;
import com.resourcemanager.service.ProjectService;

/**
 * The Class ProjectServiceImpl.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

	/** The project DAO. */
	@Autowired
	private ProjectDAO projectDAO;

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ProjectService#addProject(com.resourcemanager.model.Project)
	 */
	@Override
	@Transactional
	public void addProject(Project project) {
		this.projectDAO.addProject(project);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ProjectService#deleteProject(java.lang.Long)
	 */
	@Override
	@Transactional
	public void deleteProject(Project project) {
		this.projectDAO.removeProject(project);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ProjectService#getProjectByID(java.lang.Long)
	 */
	@Override
	@Transactional
	public Project getProjectById(Long id) {
		return this.projectDAO.getProjectByID(id);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ProjectService#listProjects()
	 */
	@Override
	@Transactional
	public List<Project> listProjects() {
		return this.projectDAO.listProjects();
	}

	/**
	 * Sets the person DAO.
	 *
	 * @param resourceDAO the new person DAO
	 */
	public void setPersonDAO(ProjectDAO resourceDAO) {
		this.projectDAO = resourceDAO;
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ProjectService#updateProject(com.resourcemanager.model.Project)
	 */
	@Override
	@Transactional
	public void updateProject(Project p) {
		this.projectDAO.updateProject(p);
	}
	
	

}
