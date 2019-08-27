
package com.resourcemanager.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Allocation;
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;
import com.resourcemanager.service.UserService;

@Controller
public class AllocationController {

	@Autowired
	private AllocationService	allocationService;

	@Autowired
	private SkillService		skillService;

	@Autowired
	private ResourceService		resourceService;

	@Autowired
	private AllocationService	projectService;
	
	@Autowired
    private UserService        userService;

	@RequestMapping(value = {"/allocations/add"}, method = RequestMethod.GET)
	public String addAllocation() {
		return "redirect:/allocations/edit/0";
	}

	@RequestMapping(value = {
	        "/allocations/delete/{id}"}, method = RequestMethod.GET)
	public String deleteAllocation(@PathVariable("id") Long id) {

		this.allocationService.deleteAllocation(id);
		return "redirect:/allocations";
	}

	@RequestMapping(value = {
	        "/allocations/edit/{id}"}, method = RequestMethod.GET)
	public String editAllocation(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("allocation",
			        this.allocationService.getAllocationByID(id));
		} else {
			model.addAttribute("allocation", new Allocation());
		}
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "allocations/edit";
	}

	@RequestMapping(value = {"/allocations"}, method = RequestMethod.GET)
	public String listAllocations(Model model) {
		model.addAttribute("listAllocations",
		        this.allocationService.listAllocations());
		return "allocations";
	}

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
	
	
	
}
