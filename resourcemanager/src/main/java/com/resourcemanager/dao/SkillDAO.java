/*
 *
 */

package com.resourcemanager.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.resourcemanager.model.Skill;

/**
 * Skill Data Access Object provides an abstract interface to some type of database or other persistence mechanism. It maps
 * application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the
 * database. This isolation supports the single responsibility principle. It separates what data access the application needs, in
 * terms of domain-specific objects and data types (the public interface of the DAO), from how these needs can be satisfied with a
 * specific DBMS, database schema, etc. (the implementation of the DAO).
 */
public interface SkillDAO {

	/**
	 * Persists a new Skill in the data source.
	 *
	 * @param skill
	 *            the skill
	 * @throws DataIntegrityViolationException
	 *             the SQL integrity constraint violation exception, such as unique
	 */
	public void addSkill(Skill skill)
		throws DataIntegrityViolationException;

	/**
	 * Removes the Skill from the data source.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteSkill(Long id);

	/**
	 * Optionally retrieves a Skill from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the skill by ID
	 */
	public Skill getSkillByID(Long id);

	/**
	 * Optionally retrieves a Skill from the data source by name if it exists.
	 *
	 * @param name
	 *            the name
	 * @return the skill by name
	 */
	public Skill getSkillByName(String name);

	/**
	 * Retrieves all Skills from the data source.
	 *
	 * @return the list
	 */
	public List<Skill> listSkills();

	/**
	 * Updates an existing Skill in the data source.
	 *
	 * @param skill
	 *            the skill
	 * @throws DataIntegrityViolationException
	 *             the SQL integrity constraint violation exception, such as unique
	 */
	public void updateSkill(Skill skill)
		throws DataIntegrityViolationException;

}
