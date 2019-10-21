/*
 *
 */
package com.resourcemanager.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Skill;

/**
 * Provides an abstract interface for a single service layer for Skills. The services do all the logic and data manipulation on
 * the data model and pass the updated model to the persistence layer.
 */
public interface SkillService {

	/**
	 * Persists a new Skill in the data source.
	 *
	 * @param skill
	 *            the skill
	 */
	@Secured("ROLE_USER")
	public void addSkill(Skill skill) throws DataIntegrityViolationException;

	/**
	 * Removes the Skill from the data source.
	 *
	 * @param lngSkillID
	 *            the lng skill ID
	 */
	@Secured("ROLE_USER")
	public void deleteSkill(Long lngSkillID);

	/**
	 * Optionally retrieves a Skill from the data source by Id if it exists.
	 *
	 * @param lngSkillID
	 *            the lng skill ID
	 * @return the skill by ID
	 */
	@Secured("ROLE_USER")
	public Skill getSkillByID(Long lngSkillID);

	/**
	 * Retrieves all Skills from the data source.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Skill> listSkills();

	/**
	 * Updates an existing Skill in the data source.
	 *
	 * @param skill
	 *            the skill
	 */
	@Secured("ROLE_USER")
	public void updateSkill(Skill skill) throws DataIntegrityViolationException;

}
