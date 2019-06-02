
package com.resourcemanager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

/**
 * The Class Skill.
 */
@Entity(name = "Skill")
@Table(name = "skill")
@NaturalIdCache
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "skill_id"))
})
public class Skill {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the projects.
	 *
	 * @return the projects
	 */
	public List<Allocation> getProjects() {
		return projects;
	}

	/**
	 * Sets the projects.
	 *
	 * @param projects the projects to set
	 */
	public void setProjects(List<Allocation> projects) {
		this.projects = projects;
	}

	/** The name. */
	@NaturalId
	private String				name;

	/** The projects. */
	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Allocation>	projects	= new ArrayList<>();

	/**
	 * Instantiates a new skill.
	 */
	public Skill() {
	}

	/**
	 * Instantiates a new skill.
	 *
	 * @param name the name
	 */
	public Skill(String name) {
		this.name = name;
	}

	// Getters and setters omitted for brevity

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Skill skill = (Skill) o;
		return Objects.equals(name, skill.name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}