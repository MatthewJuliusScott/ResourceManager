
package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Allocation;
import com.resourcemanager.model.Project;
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.service.ProjectService;
import com.resourcemanager.service.SkillService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService		projectService;

	@Autowired
	private SkillService		skillService;

	@Autowired
	private AllocationService	allocationService;

	@RequestMapping("projects/add")
	public String addProject() {
		return "redirect:/projects/edit/0";
	}

	@RequestMapping("/project/{projectId}/allocation/delete/{allocationId}")
	public String deleteAllocation(@PathVariable("projectId") Long projectId, @PathVariable("allocationId") Long allocationId,
		Model model) {
		if (projectId > 0) {
			Project project = projectService.getProjectById(projectId);
			Allocation allocation = allocationService.getAllocationById(allocationId);
			project.removeAllocation(allocation);
			this.projectService.updateProject(project);
		}
		return "redirect:/projects/edit/" + projectId;
	}

	@RequestMapping("/projects/delete/{id}")
	public String deleteProject(@PathVariable("id") Long id) {

		this.projectService.deleteProject(id);
		return "redirect:/projects";
	}

	@RequestMapping("projects/edit/{id}")
	public String editProject(@PathVariable("id") Long id, Model model) {
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
	public String saveProject(@ModelAttribute("project") Project project,
		BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}

		// if adding a new skill requirement, add that to the project
		try {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

			// TODO : check that hours does not exceed the duration in hours from start to end date, and add to front end as well
			// in a friendly error report

			ArrayList<String> ids = new ArrayList<String>();

			// extract each id
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				String key = entry.getKey();

				if (key.startsWith("allocation")) {

					String[] composite = key.split("_");
					if (composite.length == 3) {
						ids.add(composite[1]);
					}

				}

			}
			for (String id : ids) {

				// get each of the allocations
				String skillId = "0";
				String startDate = "05/09/1986";
				String endDate = "06/09/1986";
				String hours = "20";

				Allocation allocation =
					new Allocation(Long.parseLong(id), project, skillService.getSkillById(Long.parseLong(skillId)),
						LocalDate.parse(startDate, dateTimeFormatter),
						LocalDate.parse(endDate, dateTimeFormatter), Integer.parseInt(hours), null);
				project.addAllocation(allocation);
			}

		} catch (Exception e) {
			// do nothing
			e.printStackTrace();
			// TODO : friendly error reporting
		}

		if (project.getId() == 0) {
			// new project, add it
			this.projectService.addProject(project);
		} else {
			// existing project, call update
			this.projectService.updateProject(project);
		}
		return "redirect:/projects/edit/" + project.getId();
	}
}
