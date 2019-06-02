
package com.resourcemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.resourcemanager.model.Skill;
import com.resourcemanager.service.SkillService;

@Controller
public class SkillController {

	@Autowired
	private SkillService skillService;

	// For add and update skill both
	@RequestMapping(value = "/skills/save", method = RequestMethod.POST)
	public String saveSkill(@ModelAttribute("skill") Skill p,
	        BindingResult result) {
		if (result.hasErrors()) {
			System.err.println(result.toString());
		}
		if (p.getId() == 0) {
			// new skill, add it
			this.skillService.addSkill(p);
		} else {
			// existing skill, call update
			this.skillService.updateSkill(p);
		}
		return "redirect:/skills";
	}

	@RequestMapping("skills/add")
	public String addSkill() {
		return "redirect:/skills/edit/0";
	}

	@RequestMapping("skills/edit/{id}")
	public String editSkill(@PathVariable("id") Long id, Model model) {
		if (id > 0) {
			model.addAttribute("skill", this.skillService.getSkillById(id));
		} else {
			model.addAttribute("skill", new Skill());
		}
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "skills/edit";
	}
	
	@RequestMapping(value = { "/skills" }, method = RequestMethod.GET)
	public String listSkills(Model model) {
		model.addAttribute("listSkills", this.skillService.listSkills());
		return "skills";
	}

	@RequestMapping("/skill/delete/{id}")
	public String removeSkill(@PathVariable("id") Long id) {

		this.skillService.deleteSkill(id);
		return "redirect:/skills";
	}
}
