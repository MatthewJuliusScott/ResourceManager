/*
 * 
 */

package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Allocation;
import com.resourcemanager.model.Notification;
import com.resourcemanager.model.Project;
import com.resourcemanager.model.Resource;
import com.resourcemanager.model.User;
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.service.NotificationService;
import com.resourcemanager.service.ProjectService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;
import com.resourcemanager.service.UserService;

/**
 * The Class ProjectController.
 */
@Controller
public class ProjectController {

	/** The project service. */
	@Autowired
	private ProjectService		projectService;

	/** The skill service. */
	@Autowired
	private SkillService		skillService;

	/** The resource service. */
	@Autowired
	private ResourceService		resourceService;

	/** The allocation service. */
	@Autowired
	private AllocationService	allocationService;

	/** The user service. */
	@Autowired
	private UserService			userService;

	/** The notification service. */
	@Autowired
	private NotificationService	notificationService;

	/**
	 * Adds the project.
	 *
	 * @return the string
	 */
	@RequestMapping(value = {"/projects/add"}, method = RequestMethod.GET)
	public String addProject() {
		return "redirect:/projects/edit/0";
	}

	/**
	 * Delete project.
	 *
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = {
	        "/projects/delete/{id}"}, method = RequestMethod.GET)
	public String deleteProject(@PathVariable("id") Long id) {
		Project project = projectService.getProjectById(id);
		Iterator<Allocation> i = project.getAllocations().iterator();
		while (i.hasNext()) {
			Allocation allocation = i.next();
			allocationService.deleteAllocation(allocation);
			i.remove();
		}
		this.projectService.deleteProject(project);
		return "redirect:/projects";
	}

	/**
	 * Edits the project.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = {"/projects/edit/{id}"}, method = RequestMethod.GET)
	public String editProject(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("project",
			        this.projectService.getProjectById(id));
		} else {
			model.addAttribute("project", new Project());
		}
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "projects/edit";
	}

	/**
	 * Join project.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = {"/projects/join/{id}"}, method = RequestMethod.GET)
	public String joinProject(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("project",
			        this.projectService.getProjectById(id));
		} else {
			return "projects";
		}
		Object principal = SecurityContextHolder.getContext()
		        .getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		User user = this.userService.getUserByUserName(username);
		model.addAttribute("user", user);
		Resource res = user.getResource();
		model.addAttribute("userResource", res);
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "projects/join";
	}

	/**
	 * List projects.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = {"/projects"}, method = RequestMethod.GET)
	public String listProjects(Model model) {
		model.addAttribute("listProjects", this.projectService.listProjects());
		Object principal = SecurityContextHolder.getContext()
		        .getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		model.addAttribute("user",
		        this.userService.getUserByUserName(username));
		model.addAttribute("roles", this.userService.getUserByUserName(username)
		        .getAuthorityStrings());

		return "projects";
	}

	/**
	 * Save project.
	 *
	 * @param project the project
	 * @param result  the result
	 * @param request the request
	 * @return the string
	 */
	// For add and update project both
	@RequestMapping(value = "/projects/save", method = RequestMethod.POST)
	public String saveProject(@ModelAttribute("project") Project project,
	        BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}

		// if adding a new skill requirement, add that to the project
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter
		        .ofPattern("dd/MM/uuuu");

		// extract each id, and store in a set so we don't have duplicates
		HashSet<String> ids = new HashSet<String>();
		for (Entry<String, String[]> entry : request.getParameterMap()
		        .entrySet()) {
			String key = entry.getKey();

			if (Pattern.compile("allocation_\\d_\\w*").matcher(key).matches()) {

				// the input names we are looking for is
				// allocation_*id*_*fieldname*
				String[] composite = key.split("_");
				if (composite.length == 3) {
					ids.add(composite[1]);
				}
			}
		}
		
		for (String id : ids) {

			String[] skillIds = request
			        .getParameterValues("allocation_" + id + "_skillId");
			String[] startDates = request
			        .getParameterValues("allocation_" + id + "_startDate");
			String[] endDates = request
			        .getParameterValues("allocation_" + id + "_endDate");
			String[] hourss = request
			        .getParameterValues("allocation_" + id + "_hours");
			String[] resourceIds = request
			        .getParameterValues("allocation_" + id + "_resourceId");

			// Assert that all required form data was submitted, prevent saving
			// corrupt data
			if (skillIds == null || startDates == null || endDates == null
			        || hourss == null || skillIds.length != startDates.length
			        || startDates.length != endDates.length
			        || endDates.length != hourss.length) {
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
				if (resourceId != null && !resourceId.equals("")
				        && !resourceId.equals("0")) {
					resource = resourceService
					        .getResourceByID(Long.parseLong(resourceId));
				}

				Allocation allocation = new Allocation(Long.parseLong(id),
				        project,
				        skillService.getSkillByID(Long.parseLong(skillId)),
				        LocalDate.parse(startDate, dateTimeFormatter),
				        LocalDate.parse(endDate, dateTimeFormatter),
				        Integer.parseInt(hours), resource);
				if (resource != null) {
					resource.addAllocation(allocation);

					User user = resource.getUser();
					if (user != null) {
						Notification notification = new Notification(
						        "Your have been assigned a new allocation");
						notificationService.addNotification(notification);
						user.addNotification(notification);
						userService.updateUser(user);
					}

				}
				project.addAllocation(allocation);
			}
		}

		if (project.getId() == 0) {
			// new project, add it
			this.projectService.addProject(project);
		} else {
			// existing project, call update
			this.projectService.updateProject(project);
		}
		
		for (Allocation allocation : project.getAllocations()) {
			allocationService.updateAllocation(allocation);
		}
		
		return "redirect:/projects/edit/" + project.getId();
	}
}
