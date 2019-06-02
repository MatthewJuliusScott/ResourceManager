package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Project;

public interface ProjectService {

	public void addProject(Project p);

	public Project getProjectById(Long id);

	public List<Project> listProjects();

	public void deleteProject(Long id);

	public void updateProject(Project p);

}
