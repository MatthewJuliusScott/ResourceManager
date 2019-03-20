package com.resourcemanager.spring;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.spring.model.Person;
import com.resourcemanager.spring.service.PersonService;
import com.resourcemanager.spring.service.SkillService;

@Controller
public class PersonController {

	private PersonService		personService;
	private SkillService		skillService;

	@Autowired
	private ConversionService	conversionService;

	// For add and update person both
	@RequestMapping(value = "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") @Valid Person p, BindingResult result) {

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

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if (binder.getConversionService() == null) {
			binder.setConversionService(conversionService);
		}
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

	@Autowired(required = true)
	@Qualifier(value = "personService")
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@Autowired(required = true)
	@Qualifier(value = "skillService")
	public void setSSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

}
