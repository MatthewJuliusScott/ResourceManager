package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Skill;

public interface SkillService {

	@Secured("ROLE_USER")
	public void addSkill(Skill skill);

	@Secured("ROLE_USER")
	public void deleteSkill(Long id);

	@Secured("ROLE_USER")
	public Skill getSkillById(Long id);

	@Secured("ROLE_USER")
	public List<Skill> listSkills();

	@Secured("ROLE_USER")
	public void updateSkill(Skill skill);
}
