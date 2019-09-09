/*
 * 
 */

package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import com.resourcemanager.model.Resource;
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;
import com.resourcemanager.service.UserService;

/**
 * The Class AllocationController.
 */
@Controller
public class AllocationController {

	/** The allocation service. */
	@Autowired
	private AllocationService	allocationService;

	/** The skill service. */
	@Autowired
	private SkillService		skillService;

	/** The resource service. */
	@Autowired
	private ResourceService		resourceService;

	/** The project service. */
	@Autowired
	private AllocationService	projectService;
	
	/** The user service. */
	@Autowired
    private UserService        userService;
	
	/**
	 * Adds the allocation.
	 *
	 * @return the string
	 */
	@RequestMapping(value = {"/allocations/add"}, method = RequestMethod.GET)
	public String addAllocation() {
		return "redirect:/allocations/edit/0";
	}

	/**
	 * Delete allocation.
	 *
	 * @param id the id
	 * @return the string
	 */
	@RequestMapping(value = {
	        "/allocations/delete/{id}"}, method = RequestMethod.GET)
	public String deleteAllocation(@PathVariable("id") Long id) {
		Allocation allocation = allocationService.getAllocationById(id);
		allocationService.deleteAllocation(allocation);
		return "redirect:/allocations";
	}

	/**
	 * Edits the allocation.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = {
	        "/allocations/edit/{id}"}, method = RequestMethod.GET)
	public String editAllocation(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("allocation",
			        this.allocationService.getAllocationById(id));
		} else {
			model.addAttribute("allocation", new Allocation());
		}
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "allocations/edit";
	}

	/**
	 * List allocations.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = {"/allocations"}, method = RequestMethod.GET)
	public String listAllocations(Model model) {
		model.addAttribute("listAllocations",
		        this.allocationService.listAllocations());
		return "allocations";
	}

	/**
	 * List required allocations.
	 *
	 * @param model the model
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value = {
	        "/allocations/listrequired"}, method = RequestMethod.GET)
	public String listRequiredAllocations(Model model,
	        HttpServletRequest request) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate startDate;
		LocalDate endDate;
		try {
			startDate = LocalDate.parse(request.getParameter("startDate"), dateTimeFormatter);
			endDate = LocalDate.parse(request.getParameter("endDate"), dateTimeFormatter);
		} catch (Exception e) {
			startDate = LocalDate.now();
			endDate = LocalDate.now().plusDays(28);
		}
		model.addAttribute("listAllocations",
		        this.allocationService.listRequiredAllocations(startDate,
		                endDate));
		request.setAttribute("startDate", dateTimeFormatter.format(startDate));
		request.setAttribute("endDate", dateTimeFormatter.format(endDate));
		return "allocations";
	}
	
	/**
	 * Join allocation.
	 *
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/allocations/join", method = RequestMethod.POST)
	public String allocate(@ModelAttribute("resource") Resource p, BindingResult result, HttpServletRequest request) {
		
		long resourceid = Long.parseLong(request.getParameter("resourceId"));
		long allocationid = Long.parseLong(request.getParameter("allocationId"));
		Allocation allocation = this.allocationService.getAllocationById(allocationid);
		allocation.setResource(this.resourceService.getResourceByID(resourceid));
		this.allocationService.updateAllocation(allocation);

		return "projects";
	}
	
	
	
}
