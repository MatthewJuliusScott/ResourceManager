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
	
	@RequestMapping("resources/add")
	public String addResource() {
		return "redirect:/resources/edit/0";
	}

	
	@RequestMapping("resources/edit/{id}")
	public String editResource(@PathVariable("id") int id, Model model) {
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

	@RequestMapping("/delete/{id}")
	public String deleteResource(@PathVariable("id") int id) {

		this.resourceService.deleteResource(id);
		return "redirect:/resources";
	}
}
