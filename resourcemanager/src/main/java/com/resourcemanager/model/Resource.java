
package com.resourcemanager.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	private Long				id;

	/** The name. */
	private String				name;

	/** The skills. */
	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "resource_skill", joinColumns = {
			@JoinColumn(name = "resource_id") },
		inverseJoinColumns = {
				@JoinColumn(name = "skill_id") })
	@OrderColumn(name = "order_col")
	@Embedded
	private Skill[]				skills;

	/** The allocations. */
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Allocation>	allocations	= new ArrayList<>();

	/** The hours. */
	@Basic
	private int					hours;

	/**
	 * Adds the allocation.
	 *
	 * @param allocation
	 *            the allocation
	 */
	public void addAllocation(Allocation allocation) {
		allocations.add(allocation);
	}

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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Resource other = (Resource) obj;
		if (allocations == null) {
			if (other.allocations != null) {
				return false;
			}
		} else if (!allocations.equals(other.allocations)) {
			return false;
		}
		if (hours != other.hours) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
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
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<Allocation> getAllocations() {
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
	public Long getId() {
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
		return Objects.hash(name);
	}

	/**
	 * Removes the skill.
	 *
	 * @param allocation
	 *            the allocation
	 */
	public void removeAllocation(Allocation allocation) {
		for (Iterator<Allocation> iterator = allocations.iterator(); iterator
			.hasNext();) {
			Allocation i = iterator.next();

			if (i.getProject().equals(this)
				&& i.equals(allocation)) {
				iterator.remove();
			}
		}
	}

	/**
	 * Sets the allocations.
	 *
	 * @param skills
	 *            the skills to set
	 */
	public void setAllocations(List<Allocation> allocations) {
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
	public void setId(Long id) {
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
		return "Resource [id=" + id + ", name=" + name + ", skills=" + Arrays.toString(skills) + ", allocations=" + allocations
			+ ", hours=" + hours + "]";
	}

}
