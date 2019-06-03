package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Project;

public interface ProjectService {

	public void addProject(Project project);

	public void deleteProject(Long id);

	public Project getProjectById(Long id);

	public List<Project> listProjects();

	public void updateProject(Project project);

}
