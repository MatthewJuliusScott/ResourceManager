package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Skill;

public interface SkillService {

	public void addSkill(Skill skill);

	public void deleteSkill(Long id);

	public Skill getSkillById(Long id);

	public List<Skill> listSkills();

	public void updateSkill(Skill skill);
}
