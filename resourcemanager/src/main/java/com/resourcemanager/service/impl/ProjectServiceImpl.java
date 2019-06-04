package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.ProjectDAO;
import com.resourcemanager.model.Project;
import com.resourcemanager.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	@Override
	@Transactional
	public void addProject(Project project) {
		this.projectDAO.addProject(project);
	}

	@Override
	@Transactional
	public void deleteProject(Long id) {
		this.projectDAO.removeProject(id);
	}

	@Override
	@Transactional
	public Project getProjectById(Long id) {
		return this.projectDAO.getProjectById(id);
	}

	@Override
	@Transactional
	public List<Project> listProjects() {
		return this.projectDAO.listProjects();
	}

	public void setPersonDAO(ProjectDAO resourceDAO) {
		this.projectDAO = resourceDAO;
	}

	@Override
	@Transactional
	public void updateProject(Project p) {
		this.projectDAO.updateProject(p);
	}

}
