package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Skill;

public interface SkillDAO {

	public void addSkill(Skill skill);

	public void deleteSkill(Long id);

	public Skill getSkillByID(Long id);

	public Skill getSkillByName(String name);

	public List<Skill> listSkills();

	public void updateSkill(Skill skill);
	
}
