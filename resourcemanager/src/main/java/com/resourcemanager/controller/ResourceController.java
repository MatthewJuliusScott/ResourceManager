/*
 *
 */
package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.resourcemanager.model.Resource;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;
import com.resourcemanager.service.UserService;

/**
 * This controller responds to the user input and uses the service layer to create, read, update or delete the Resource data model
 * objects. This controller receives the input, optionally validates it and then passes the input to the model and directs the
 * user back to a view to display the model and accept further user input.
 */
@Controller
public class ResourceController {

	/** The resource service. */
	@Autowired
	private ResourceService	resourceService;

	/** The skill service. */
	@Autowired
	private SkillService	skillService;

	/** The user service. */
	@Autowired
	private UserService		userService;

	/**
	 * Directs the user to the edit Resource view but with an id of 0 in order to create a new Resource only.
	 *
	 * @return the string
	 */
	@RequestMapping("resources/add")
	public String addResource() {
		return "redirect:/resources/edit/0";
	}

	/**
	 * Deletes a particular Resource from the application cache and persistence layer, updating the associated Allocations, Skills
	 * and Projects.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = { "resources/delete/{id}" }, method = RequestMethod.GET)
	public String deleteResource(@PathVariable("id") Long id) {

		Resource resource = this.resourceService.getResourceByID(id);
		if (resource.getUser() != null) {
			resource.getUser().setResource(null);
			userService.updateUser(resource.getUser());
		}

		this.resourceService.deleteResource(id);
		return "redirect:/resources";
	}

	/**
	 * Passes the data model to the edit Resource view, and redirects the user to that view to respond to their input.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/resources/edit/{id}" }, method = RequestMethod.GET)
	public String editResource(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("resource", this.resourceService.getResourceByID(id));
		} else {
			model.addAttribute("resource", new Resource());
		}
		model.addAttribute("listResources", this.resourceService.listResources());
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "resources/edit";
	}

	/**
	 * Retrieves all Resources from the persistence layer and passes them to the Resources view to display them to the user and
	 * respond to their input.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/resources" }, method = RequestMethod.GET)
	public String listResources(Model model) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("listResources", this.resourceService.listResources());
		return "resources";
	}

	/**
	 * Takes the user input submitted via the edit Resource view and merges changes to the data model for the Resource and
	 * associated Allocations and Skills before passing that to the persistence layer.
	 *
	 * @param resource
	 *            the resource
	 * @param result
	 *            the result
	 * @return the string
	 */
	// For add and update resource both
	@RequestMapping(value = "/resources/save", method = RequestMethod.POST)
	public String saveResource(@ModelAttribute("resource") Resource resource, BindingResult result) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}
		if (resource.getId() == 0) {
			// new resource, add it
			this.resourceService.addResource(resource);
		} else {
			// existing resource, call update
			this.resourceService.updateResource(resource);
		}
		return "redirect:/resources";
	}

	/**
	 * Retrieves all Resources that match the search criteria of Skill, hours and no allocations during the specified time period
	 * from the persistence layer and passes them to the Resources view to display them to the user and respond to their input.
	 *
	 * @param model
	 *            the model
	 * @param skillId
	 *            the skill id
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param hours
	 *            the hours
	 * @return the string
	 */
	@RequestMapping(value = { "/resources/search" }, method = RequestMethod.GET)
	public String searchResources(Model model, @RequestParam("skillId") String skillId,
		@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
		@RequestParam("hours") String hours) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

		List<Resource> resources = new ArrayList<Resource>();

		resources = this.resourceService.searchResources(Long.parseLong(skillId),
			LocalDate.parse(startDate, dateTimeFormatter), LocalDate.parse(endDate, dateTimeFormatter),
			Integer.parseInt(hours));

		model.addAttribute("resource", new Resource());
		model.addAttribute("listResources", resources);
		return "resources/searchResult";
	}
}
