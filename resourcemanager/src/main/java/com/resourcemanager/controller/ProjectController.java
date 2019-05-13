package com.resourcemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Project;
import com.resourcemanager.service.ProjectService;
import com.resourcemanager.service.SkillService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService	projectService;

	@Autowired
	private SkillService	skillService;

	// For add and update project both
	@RequestMapping(value = "/project/add", method = RequestMethod.POST)
	public String addProject(@ModelAttribute("project") Project p, BindingResult result) {

		if (result.hasErrors()) {
			System.err.println(result.toString());
		}

		if (p.getId() == 0) {
			// new project, add it
			this.projectService.addProject(p);
		} else {
			// existing project, call update
			this.projectService.updateProject(p);
		}

		return "redirect:/projects";

	}

	
	@RequestMapping("/project/edit/{id}")
	public String editProject(@PathVariable("id") int id, Model model) {
		model.addAttribute("project", this.projectService.getProjectById(id));
		model.addAttribute("listProjects", this.projectService.listProjects());
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "project";
	}

	@RequestMapping(value = { "/projects" }, method = RequestMethod.GET)
	public String listProjects(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("listProjects", this.projectService.listProjects());
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "project";
	}

	@RequestMapping("/project/delete/{id}")
	public String removeProject(@PathVariable("id") int id) {

		this.projectService.deleteProject(id);
		return "redirect:/projects";
	}
}
