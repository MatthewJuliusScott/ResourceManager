
package com.resourcemanager.model;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * The Class Resource.
 */
@Entity
@Table(name = "resource")
public class Resource {

	/** The id. */
	@Id
	@Column(name = "resource_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;

	/** The name. */
	private String			name;

	/** The skills. */
	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "resource_skill", joinColumns = {
			@JoinColumn(name = "resource_id") },
		inverseJoinColumns = {
				@JoinColumn(name = "skill_id") })
	@OrderColumn(name = "order_col")
	@Embedded
	private Skill[]			skills;

	/** The allocations. */
	@ManyToOne(optional = true, cascade = { CascadeType.MERGE })
	@JoinTable(name = "project_resource_allocation", joinColumns = {
			@JoinColumn(columnDefinition = "integer", name = "resource_id", nullable = true) },
		inverseJoinColumns = {
				@JoinColumn(columnDefinition = "integer", name = "allocation_id", nullable = true) })
	@OrderColumn(name = "order_col")
	@Embedded
	private Allocation[]	allocations;

	/** The hours. */
	@Basic
	private int				hours;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Resource)) {
			return false;
		}
		Resource other = (Resource) obj;
		if (!Arrays.equals(allocations, other.allocations)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (!Arrays.equals(skills, other.skills)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the allocations.
	 *
	 * @return the allocations
	 */
	public Allocation[] getAllocations() {
		return allocations;
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
	public int getId() {
		return id;
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
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public Skill[] getSkills() {
		return skills;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(allocations);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(skills);
		return result;
	}

	/**
	 * Sets the allocations.
	 *
	 * @param allocations
	 *            the allocations to set
	 */
	public void setAllocations(Allocation[] allocations) {
		this.allocations = allocations;
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
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills
	 *            the new skills
	 */
	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", skills="
			+ Arrays.toString(skills) + ", allocations="
			+ Arrays.toString(allocations) + "]";
	}
}
