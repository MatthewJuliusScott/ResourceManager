package com.resourcemanager.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.spring.dao.SkillDAO;
import com.resourcemanager.spring.model.Skill;

@Service
public class SkillServiceImpl implements SkillService {
	
	private SkillDAO skillDAO;

	public void setSkillDAO(SkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	@Override
	@Transactional
	public void addSkill(Skill skill) {
		this.skillDAO.addSkill(skill);
	}

	@Override
	@Transactional
	public void updateSkill(Skill skill) {
		this.skillDAO.updateSkill(skill);
	}

	@Override
	@Transactional
	public List<Skill> listSkills() {
		return this.skillDAO.listSkills();
	}

	@Override
	@Transactional
	public Skill getSkillById(int id) {
		return this.skillDAO.getSkillById(id);
	}

	@Override
	@Transactional
	public void removeSkill(int id) {
		this.skillDAO.removeSkill(id);
	}

}
