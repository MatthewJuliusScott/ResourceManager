package com.resourcemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Person;
import com.resourcemanager.service.PersonService;
import com.resourcemanager.service.SkillService;

@Controller
public class PersonController {

	@Autowired
	private PersonService	personService;

	@Autowired
	private SkillService	skillService;

	// For add and update person both
	@RequestMapping(value = "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p, BindingResult result) {

		if (result.hasErrors()) {
			System.err.println(result.toString());
		}

		if (p.getId() == 0) {
			// new person, add it
			this.personService.addPerson(p);
		} else {
			// existing person, call update
			this.personService.updatePerson(p);
		}

		return "redirect:/persons";

	}

	
	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") int id, Model model) {
		model.addAttribute("person", this.personService.getPersonById(id));
		model.addAttribute("listPersons", this.personService.listPersons());
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "person";
	}

	@RequestMapping(value = { "/persons", "/index", "/" }, method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "person";
	}

	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") int id) {

		this.personService.removePerson(id);
		return "redirect:/persons";
	}
}
