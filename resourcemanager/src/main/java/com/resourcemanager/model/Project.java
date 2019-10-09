/*
 *
 */

package com.resourcemanager.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Class Project.
 */
@Entity(name = "Project")
@Table(name = "project")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "project_id")) })
public class Project implements Cloneable {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long				id;

	/** The name. */
	private String				name;

	/** The pob. */
	@Column(unique = true)
	private String				pob;

	/** The allocations. */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id")
	private List<Allocation>	allocations	= new ArrayList<Allocation>();

	/**
	 * Instantiates a new project.
	 */
	public Project() {
	}

	/**
	 * Instantiates a new project.
	 *
	 * @param name
	 *            the name
	 */
	public Project(String name) {
		this.name = name;
	}

	/**
	 * Adds the allocation.
	 *
	 * @param allocation
	 *            the allocation
	 */
	public void addAllocation(Allocation allocation) {
		allocations.add(allocation);
		if (allocation.getResource() != null) {
			allocation.getResource().addAllocation(allocation);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Project clone = (Project) super.clone();
		clone.allocations = new ArrayList<Allocation>();
		if (allocations != null) {
			for (Allocation allocation : allocations) {
				clone.getAllocations().add(allocation);
			}
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

		Project project = (Project) o;
		return Objects.equals(name, project.name);
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
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
	 * Gets the pob.
	 *
	 * @return the pob
	 */
	public String getPob() {
		return pob;
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

			if (i.getProject().equals(this) && i.equals(allocation)) {
				i.setProject(null);
				iterator.remove();
			}
		}
	}

	/**
	 * Sets the allocations.
	 *
	 * @param allocations
	 *            the new allocations
	 */
	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
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
	 * Sets the pob.
	 *
	 * @param pob
	 *            the new pob
	 */
	public void setPob(String pob) {
		this.pob = pob;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + "]";
	}
}