package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Project;

public interface ProjectDAO {

	public void addProject(Project p);
	public void updateProject(Project p);
	public List<Project> listProjects();
	public Project getProjectById(Long id);
	public void removeProject(Long id);
}
