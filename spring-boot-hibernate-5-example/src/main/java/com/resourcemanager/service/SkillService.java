package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Skill;

public interface SkillService {

	public void addSkill(Skill skill);

	public Skill getSkillById(int id);

	public List<Skill> listSkills();

	public void removeSkill(int id);

	public void updateSkill(Skill skill);
}
