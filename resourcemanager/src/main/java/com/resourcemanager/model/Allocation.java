
package com.resourcemanager.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * The Class Allocation.
 */
@Entity(name = "Allocation")
@Table(name = "project_skill")
public class Allocation {

	/** The id. */
	@EmbeddedId
	private AllocationId	id;

	/** The project. */
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("projectId")
	private Project			project;

	/** The skill. */
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("skillId")
	private Skill			skill;

	/** The start date. */
	@Column(name = "start_date")
	private LocalDate		startDate;

	/** The end date. */
	@Column(name = "end_date")
	private LocalDate		endDate;

	/** The hours. */
	private int				hours;

	/** The resource. */
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "resource_id")
	@OrderColumn(name = "order_col")
	private Resource		resource;

	/**
	 * Instantiates a new allocation.
	 */
	public Allocation() {
	}

	/**
	 * Instantiates a new allocation.
	 *
	 * @param project the project
	 * @param skill   the skill
	 */
	public Allocation(Project project, Skill skill) {
		this.project = project;
		this.skill = skill;
		this.id = new AllocationId(project.getId(), skill.getId());
	}

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

		Allocation that = (Allocation) o;
		return Objects.equals(project, that.project)
		        && Objects.equals(skill, that.skill);
	}

	/**
	 * Gets the end date.
	 *
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Gets the hours.
	 *
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public AllocationId getId() {
		return id;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Gets the resource.
	 *
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * Gets the skill.
	 *
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(project, skill);
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Sets the hours.
	 *
	 * @param hours the new hours
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(AllocationId id) {
		this.id = id;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Sets the resource.
	 *
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * Sets the skill.
	 *
	 * @param skill the skill to set
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
}