package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Project;

public interface ProjectDAO {

	public void addProject(Project p);
	public void updateProject(Project p);
	public List<Project> listProjects();
	public Project getProjectById(int id);
	public void removeProject(int id);
}
