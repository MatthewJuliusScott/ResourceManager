package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Project;

public interface ProjectService {

	@Secured("ROLE_ADMIN")
	public void addProject(Project project);

	@Secured("ROLE_ADMIN")
	public void deleteProject(Long id);

	@Secured("ROLE_ADMIN")
	public Project getProjectByID(Long id);

	@Secured("ROLE_USER")
	public List<Project> listProjects();

	@Secured("ROLE_ADMIN")
	public void updateProject(Project project);
	
	@Secured("ROLE_USER")
	public List<Project> viewProjects();

	

}
