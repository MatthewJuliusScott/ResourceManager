/*
 * 
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Skill;

/**
 * The Interface SkillService.
 */
public interface SkillService {

	/**
	 * Adds the skill.
	 *
	 * @param skill the skill
	 */
	@Secured("ROLE_USER")
	public void addSkill(Skill skill) throws DataIntegrityViolationException;

	/**
	 * Delete skill.
	 *
	 * @param lngSkillID the lng skill ID
	 */
	@Secured("ROLE_USER")
	public void deleteSkill(Long lngSkillID);

	/**
	 * Gets the skill by ID.
	 *
	 * @param lngSkillID the lng skill ID
	 * @return the skill by ID
	 */
	@Secured("ROLE_USER")
	public Skill getSkillByID(Long lngSkillID);

	/**
	 * List skills.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Skill> listSkills();

	/**
	 * Update skill.
	 *
	 * @param skill the skill
	 */
	@Secured("ROLE_USER")
	public void updateSkill(Skill skill) throws DataIntegrityViolationException;
	

	
}
