package com.resourcemanager.spring.service;

import java.util.List;

import com.resourcemanager.spring.model.Person;
import com.resourcemanager.spring.model.Skill;

public interface SkillService {

	public void addSkill(Skill skill);
	public void updateSkill(Skill skill);
	public List<Skill> listSkills();
	public Skill getSkillById(int id);
	public void removeSkill(int id);
}
