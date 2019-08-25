package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Project;

public interface ProjectDAO {

	public void addProject(Project project);

	public Project getProjectByID(Long id);

	public List<Project> listProjects();

	public void removeProject(Long id);

	public void updateProject(Project project);
	
}
