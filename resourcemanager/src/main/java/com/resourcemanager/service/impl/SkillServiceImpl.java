/*
 *
 */
package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.SkillDAO;
import com.resourcemanager.model.Skill;
import com.resourcemanager.service.SkillService;

/**
 * The Concrete implementation of SkillService.
 */
@Service
public class SkillServiceImpl implements SkillService {

	/** The skill DAO. */
	@Autowired
	private SkillDAO skillDAO;

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.service.SkillService#addSkill(com.resourcemanager.model.Skill)
	 */
	@Override
	@Transactional
	public void addSkill(Skill skill) throws DataIntegrityViolationException {
		this.skillDAO.addSkill(skill);
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.service.SkillService#deleteSkill(java.lang.Long)
	 */
	@Override
	@Transactional
	public void deleteSkill(Long lngSkillID) {
		this.skillDAO.deleteSkill(lngSkillID);
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.service.SkillService#getSkillByID(java.lang.Long)
	 */
	@Override
	@Transactional
	public Skill getSkillByID(Long lngSkillID) {
		return this.skillDAO.getSkillByID(lngSkillID);
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.service.SkillService#listSkills()
	 */
	@Override
	@Transactional
	public List<Skill> listSkills() {
		return this.skillDAO.listSkills();
	}

	/**
	 * Sets the skill DAO.
	 *
	 * @param skillDAO
	 *            the new skill DAO
	 */
	public void setSkillDAO(SkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.resourcemanager.service.SkillService#updateSkill(com.resourcemanager.model.Skill)
	 */
	@Override
	@Transactional
	public void updateSkill(Skill skill) throws DataIntegrityViolationException {
		this.skillDAO.updateSkill(skill);
	}
}
