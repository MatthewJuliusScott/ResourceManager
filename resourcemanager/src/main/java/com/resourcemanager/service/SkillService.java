package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Skill;

public interface SkillService {

	public void addSkill(Skill skill);

	public Skill getSkillById(Long id);

	public List<Skill> listSkills();

	public void deleteSkill(Long id);

	public void updateSkill(Skill skill);
}
