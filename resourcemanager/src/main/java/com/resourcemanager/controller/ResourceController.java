package com.resourcemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Resource;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;

@Controller
public class ResourceController {

	@Autowired
	private ResourceService	resourceService;

	@Autowired
	private SkillService	skillService;

	// For add and update resource both
	@RequestMapping(value = "/resource/add", method = RequestMethod.POST)
	public String addResource(@ModelAttribute("resource") Resource p, BindingResult result) {

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

	
	@RequestMapping("/edit/{id}")
	public String editResource(@PathVariable("id") int id, Model model) {
		model.addAttribute("resource", this.resourceService.getResourceById(id));
		model.addAttribute("listResources", this.resourceService.listResources());
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "resource";
	}

	@RequestMapping(value = { "/resources" }, method = RequestMethod.GET)
	public String listResources(Model model) {
		model.addAttribute("resource", new Resource());
		model.addAttribute("listResources", this.resourceService.listResources());
		return "resource";
	}

	@RequestMapping("/delete/{id}")
	public String deleteResource(@PathVariable("id") int id) {

		this.resourceService.deleteResource(id);
		return "redirect:/resources";
	}
}
