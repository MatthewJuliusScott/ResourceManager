package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Skill;

public interface SkillDAO {

	public void addSkill(Skill skill);
	public void updateSkill(Skill skill);
	public List<Skill> listSkills();
	public Skill getSkillById(int id);
	public void removeSkill(int id);
}
