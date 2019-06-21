package com.resourcemanager.functionality;

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

@Controller
public class ResourceController {

	@Autowired
	private ResourceService	resourceService;

	@Autowired
	private SkillService	skillService;

	@RequestMapping("resources/add")
	public String addResource() {
		return "redirect:/resources/edit/0";
	}

	@RequestMapping("resources/delete/{id}")
	public String deleteResource(@PathVariable("id") Long id) {

		this.resourceService.deleteResource(id);
		return "redirect:/resources";
	}

	@RequestMapping(value = { "/resources/edit/{id}" }, method = RequestMethod.GET)
	public String editResource(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("resource", this.resourceService.getResourceById(id));
		} else {
			model.addAttribute("resource", new Resource());
		}
		model.addAttribute("listResources", this.resourceService.listResources());
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "resources/edit";
	}

	@RequestMapping(value = { "/resources" }, method = RequestMethod.GET)
	public String listResources(Model model) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("listResources", this.resourceService.listResources());
		return "resources";
	}

	// For add and update resource both
	@RequestMapping(value = "/resources/save", method = RequestMethod.POST)
	public String saveResource(@ModelAttribute("resource") Resource p, BindingResult result) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}
		if (p.getId() == 0) {
			// new resource, add it
			this.resourceService.addResource(p);
		} else {
			// existing resource, call update
			this.resourceService.updateResource(p);
		}
		return "redirect:/resources";
	}

	@RequestMapping(value = { "/resources/search" }, method = RequestMethod.GET)
	public String searchResources(Model model, @RequestParam("skillId") String skillId,
		@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
		@RequestParam("hours") String hours) {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

		List<Resource> resources = new ArrayList<Resource>();

		try {
			resources = this.resourceService.searchResources(Long.parseLong(skillId),
				LocalDate.parse(startDate, dateTimeFormatter), LocalDate.parse(endDate, dateTimeFormatter),
				Integer.parseInt(hours));
		} catch (Exception e) {
			// TODO : friendly error reporting
		}

		model.addAttribute("resource", new Resource());
		model.addAttribute("listResources", resources);
		return "resources/searchResult";
	}
}
