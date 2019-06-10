
package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.regex.Pattern;

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
import com.resourcemanager.model.Resource;
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.service.ProjectService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;

@Controller
public class ProjectController {

	@Autowired
	private ProjectService		projectService;

	@Autowired
	private SkillService		skillService;

	@Autowired
	private ResourceService		resourceService;

	@Autowired
	private AllocationService	allocationService;

	@RequestMapping("projects/add")
	public String addProject() {
		return "redirect:/projects/edit/0";
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

			// extract each id, and store in a set so we don't have duplicates
			HashSet<String> ids = new HashSet<String>();
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
				String key = entry.getKey();

				if (Pattern.compile("allocation_\\d_\\w*").matcher(key).matches()) {

					// the input names we are looking for is allocation_*id*_*fieldname*
					String[] composite = key.split("_");
					if (composite.length == 3) {
						ids.add(composite[1]);
					}
				}
			}

			for (String id : ids) {

				String[] skillIds = request.getParameterValues("allocation_" + id + "_skillId");
				String[] startDates = request.getParameterValues("allocation_" + id + "_startDate");
				String[] endDates = request.getParameterValues("allocation_" + id + "_endDate");
				String[] hourss = request.getParameterValues("allocation_" + id + "_hours");
				String[] resourceIds = request.getParameterValues("allocation_" + id + "_resourceId");

				// Assert that all required form data was submitted, prevent saving corrupt data
				if (skillIds == null || startDates == null || endDates == null || hourss == null
					|| skillIds.length != startDates.length || startDates.length != endDates.length
					|| endDates.length != hourss.length) {

					// TODO : friendly error reporting

					continue;
				}

				for (int i = 0; i < skillIds.length; i++) {

					// get each of the allocations
					String skillId = skillIds[i];
					String startDate = startDates[i];
					String endDate = endDates[i];
					String hours = hourss[i];
					String resourceId = resourceIds != null ? resourceIds[i] : null;

					Resource resource = null;
					if (resourceId != null && !resourceId.equals("") && !resourceId.equals("0")) {
						resource = resourceService.getResourceById(Long.parseLong(resourceId));
					}

					Allocation allocation =
						new Allocation(Long.parseLong(id), project, skillService.getSkillById(Long.parseLong(skillId)),
							LocalDate.parse(startDate, dateTimeFormatter),
							LocalDate.parse(endDate, dateTimeFormatter), Integer.parseInt(hours), resource);
					if (resource != null) {
						resource.addAllocation(allocation);
					}
					project.addAllocation(allocation);
					
					allocationService.updateAllocation(allocation);

				}
			}

		} catch (

		Exception e) {
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
