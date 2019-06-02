
package com.resourcemanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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

/**
 * The Class Project.
 */
@Entity(name = "Project")
@Table(name = "project")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "project_id"))
})
public class Project {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long				id;

	/** The name. */
	private String				name;

	/** The allocations. */
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Allocation>	allocations	= new ArrayList<>();

	/**
	 * Instantiates a new project.
	 */
	public Project() {
	}

	/**
	 * Instantiates a new project.
	 *
	 * @param title the title
	 */
	public Project(String title) {
		this.name = title;
	}

	/**
	 * Adds the skill.
	 *
	 * @param skill     the skill
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @param hours     the hours
	 */
	public void addAllocation(Skill skill, LocalDate startDate, LocalDate endDate,
	        int hours) {
		Allocation allocation = new Allocation(this, skill);
		allocation.setStartDate(startDate);
		allocation.setEndDate(endDate);
		allocation.setHours(hours);
		allocations.add(allocation);
		skill.getProjects().add(allocation);
	}

	/**
	 * Removes the skill.
	 *
	 * @param skill the skill
	 */
	public void removeSkill(Skill skill) {
		for (Iterator<Allocation> iterator = allocations.iterator(); iterator
		        .hasNext();) {
			Allocation allocation = iterator.next();

			if (allocation.getProject().equals(this)
			        && allocation.getSkill().equals(skill)) {
				iterator.remove();
				allocation.getSkill().getProjects().remove(allocation);
				allocation.setProject(null);
				allocation.setSkill(null);
			}
		}
	}

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
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<Allocation> getAllocations() {
		return allocations;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills the skills to set
	 */
	public void setSkills(List<Allocation> allocations) {
		this.allocations = allocations;
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

		Project project = (Project) o;
		return Objects.equals(name, project.name);
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