/*
 *
 */

package com.resourcemanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Skill;
import com.resourcemanager.service.SkillService;

/**
 * This controller responds to the user input and uses the service layer to create, read, update or delete the Skill data model
 * objects. This controller receives the input, optionally validates it and then passes the input to the model and directs the
 * user back to a view to display the model and accept further user input.
 */
@Controller
public class SkillController {

	/** The skill service. */
	@Autowired
	private SkillService skillService;

	/**
	 * Directs the user to the edit Skill view but with an id of 0 in order to create a new Skill only.
	 *
	 * @return the string
	 */
	@RequestMapping("skills/add")
	public String addSkill() {
		return "redirect:/skills/edit/0";
	}

	/**
	 * Passes the data model to the edit Skill view, and redirects the user to that view to respond to their input.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/skills/edit/{id}" }, method = RequestMethod.GET)
	public String editSkill(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("skill", this.skillService.getSkillByID(id));
		} else {
			model.addAttribute("skill", new Skill());
		}
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "skills/edit";
	}

	/**
	 * Retrieves all Skills from the persistence layer and passes them to the Resources view to display them to the user and
	 * respond to their input.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/skills" }, method = RequestMethod.GET)
	public String listSkills(Model model) {
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "skills";
	}

	/**
	 * Deletes a particular Skill from the application cache and persistence layer, updating the associated Allocations and
	 * Resources.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	@RequestMapping(value = { "/skills/delete/{id}" }, method = RequestMethod.GET)
	public String removeSkill(@PathVariable("id") Long id) {

		this.skillService.deleteSkill(id);
		return "redirect:/skills";
	}

	/**
	 * Takes the user input submitted via the edit Skill view and merges changes to the data model for the Skill and associated
	 * Allocations and Resources before passing that to the persistence layer.
	 *
	 * @param skill
	 *            the skill
	 * @param result
	 *            the result
	 * @return the string
	 */
	// For add and update skill both
	@RequestMapping(value = "/skills/save", method = RequestMethod.POST)
	public String saveSkill(@ModelAttribute("skill") Skill skill,
		BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}
		try {
			if (skill.getId() == 0) {
				// new skill, add it
				this.skillService.addSkill(skill);
			} else {
				// existing skill, call update
				this.skillService.updateSkill(skill);
			}
		} catch (DataIntegrityViolationException dive) {
			HttpServletRequestDecorator req = new HttpServletRequestDecorator(
				request);
			req.addMessage("Skill " + skill.getName() + " already exists.");
			return "redirect:/skills";
		}
		return "redirect:/skills";
	}
}
