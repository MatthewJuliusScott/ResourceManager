/*
 * 
 */

package com.resourcemanager.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.resourcemanager.model.Skill;

/**
 * The Interface SkillDAO.
 */
public interface SkillDAO {

	/**
	 * Adds the skill.
	 *
	 * @param skill the skill
	 * @throws DataIntegrityViolationException the SQL integrity
	 *                                                  constraint violation
	 *                                                  exception
	 */
	public void addSkill(Skill skill)
	        throws DataIntegrityViolationException;

	/**
	 * Delete skill.
	 *
	 * @param id the id
	 */
	public void deleteSkill(Long id);

	/**
	 * Gets the skill by ID.
	 *
	 * @param id the id
	 * @return the skill by ID
	 */
	public Skill getSkillByID(Long id);

	/**
	 * Gets the skill by name.
	 *
	 * @param name the name
	 * @return the skill by name
	 */
	public Skill getSkillByName(String name);

	/**
	 * List skills.
	 *
	 * @return the list
	 */
	public List<Skill> listSkills();

	/**
	 * Update skill.
	 *
	 * @param skill the skill
	 * @throws DataIntegrityViolationException the SQL integrity
	 *                                                  constraint violation
	 *                                                  exception
	 */
	public void updateSkill(Skill skill)
	        throws DataIntegrityViolationException;

}
