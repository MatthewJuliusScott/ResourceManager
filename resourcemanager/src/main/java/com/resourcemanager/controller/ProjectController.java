
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

	@RequestMapping("projects/add")
	public String addProject() {
		return "redirect:/projects/edit/0";
	}

	@RequestMapping("/projects/delete/{id}")
	public String deleteProject(@PathVariable("id") int id) {

		this.projectService.deleteProject(id);
		return "redirect:/projects";
	}

	@RequestMapping("projects/edit/{id}")
	public String editProject(@PathVariable("id") int id, Model model) {
		if (id > 0) {
			model.addAttribute("project", this.projectService.getProjectById(id));
		} else {
			model.addAttribute("project", new Project());
		}
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "projects/edit";
	}

	@RequestMapping(value = { "/projects" }, method = RequestMethod.GET)
	public String listProjects(Model model) {
		model.addAttribute("listProjects", this.projectService.listProjects());
		return "projects";
	}

	// For add and update project both
	@RequestMapping(value = "/projects/save", method = RequestMethod.POST)
	public String saveProject(@ModelAttribute("project") Project p,
		BindingResult result) {
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
}
