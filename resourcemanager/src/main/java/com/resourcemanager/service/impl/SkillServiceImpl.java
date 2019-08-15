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
	public void deleteSkill(Long lngSkillID) {
		this.skillDAO.deleteSkill(lngSkillID);
	}

	@Override
	@Transactional
	public Skill getSkillByID(Long lngSkillID) {
		return this.skillDAO.getSkillByID(lngSkillID);
	}

	@Override
	@Transactional
	public List<Skill> listSkills() {
		return this.skillDAO.listSkills();
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
