package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.SkillDAO;
import com.resourcemanager.model.Skill;
import com.resourcemanager.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillDAO skillDAO;

	@Override
	@Transactional
	public void addSkill(Skill skill) {
		this.skillDAO.addSkill(skill);
	}

	@Override
	@Transactional
	public Skill getSkillById(int id) {
		return this.skillDAO.getSkillById(id);
	}

	@Override
	@Transactional
	public List<Skill> listSkills() {
		return this.skillDAO.listSkills();
	}

	@Override
	@Transactional
	public void deleteSkill(int id) {
		this.skillDAO.deleteSkill(id);
	}

	public void setSkillDAO(SkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	@Override
	@Transactional
	public void updateSkill(Skill skill) {
		this.skillDAO.updateSkill(skill);
	}

}
