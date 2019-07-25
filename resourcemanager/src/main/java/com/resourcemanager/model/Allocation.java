
package com.resourcemanager.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 * The Class Allocation.
 */
@Entity(name = "Allocation")
@Table(name = "allocation")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "allocation_id")) })
public class Allocation implements Cloneable {

	/** The Constant formatter. */
	public static final DateTimeFormatter	formatter	= DateTimeFormatter
		.ofPattern("dd/MM/yyyy");

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long							id;

	/** The project. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private Project							project;

	/** The skill. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "skill_id")
	private Skill							skill;

	/** The start date. */
	@Column(name = "start_date")
	private LocalDate						startDate;

	/** The end date. */
	@Column(name = "end_date")
	private LocalDate						endDate;

	/** The hours. */
	private int								hours;

	/** The resource. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "resource_id")
	@OrderColumn(name = "order_col")
	private Resource						resource;

	/**
	 * Instantiates a new allocation.
	 */
	public Allocation() {
	}

	/**
	 * Instantiates a new allocation.
	 *
	 * @param id
	 *            the id
	 * @param project
	 *            the project
	 * @param skill
	 *            the skill
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @param hours
	 *            the hours
	 * @param resource
	 *            the resource
	 */
	public Allocation(Long id, Project project, Skill skill,
		LocalDate startDate, LocalDate endDate, int hours,
		Resource resource) {
		super();
		this.id = id;
		this.project = project;
		this.skill = skill;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hours = hours;
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Allocation clone = (Allocation) super.clone();
		if (project != null) {
			clone.project = (Project) project.clone();
		}
		if (skill != null) {
			clone.skill = (Skill) skill.clone();
		}
		if (resource != null) {
			clone.resource = (Resource) resource.clone();
		}
		return clone;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

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
	 * Gets the end date.
	 *
	 * @return the endDate formatted as a String
	 */
	public String getEndDateAsString() {
		return endDate.format(formatter);
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
	public long getId() {
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

	/**
	 * Gets the start date.
	 *
	 * @return the startDate formatted as a String
	 */
	public String getStartDateAsString() {
		return startDate.format(formatter);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(project, skill);
	}

	/**
	 * Pre remove. This removes the object from associations to let hibernate delete persist correctly.
	 */
	@PreRemove
	public void preRemove() {
		if (getProject() != null) {
			getProject().removeAllocation(this);
		}
		setProject(null);
		if (getResource() != null) {
			getResource().removeAllocation(this);
		}
		setResource(null);
		if (getSkill() != null) {
			getSkill().removeAllocation(this);
		}
		setSkill(null);
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Sets the hours.
	 *
	 * @param hours
	 *            the new hours
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Sets the project.
	 *
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Sets the resource.
	 *
	 * @param resource
	 *            the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * Sets the skill.
	 *
	 * @param skill
	 *            the skill to set
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Allocation [id=" + id + ", project=" + project + ", skill="
			+ skill + ", startDate=" + startDate + ", endDate=" + endDate
			+ ", hours=" + hours + ", resource=" + resource + "]";
	}
}