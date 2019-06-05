
package com.resourcemanager.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private long				id;

	/** The name. */
	@NaturalId
	private String				name;

	/** The resources. */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "resource_skill",
		joinColumns = {
				@JoinColumn(
					name = "skill_id")
		},
		inverseJoinColumns = {
				@JoinColumn(
					name = "resource_id")
		})
	private List<Resource>		resources	= new ArrayList<Resource>();

	/** The allocations. */
	@ManyToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Allocation>	allocations	= new ArrayList<>();

	/**
	 * Instantiates a new skill.
	 */
	public Skill() {
	}

	/**
	 * Instantiates a new skill.
	 *
	 * @param name
	 *            the name
	 */
	public Skill(String name) {
		this.name = name;
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
		Skill other = (Skill) obj;
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
		return true;
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
	 * Gets the resources.
	 *
	 * @return the resources
	 */
	public List<Resource> getResources() {
		return resources;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Removes the allocation.
	 *
	 * @param allocation
	 *            the allocation
	 */
	public void removeAllocation(Allocation allocation) {
		for (Iterator<Allocation> iterator = allocations.iterator(); iterator
			.hasNext();) {
			Allocation i = iterator.next();

			if (i.getSkill().equals(this)
				&& i.equals(allocation)) {
				iterator.remove();
			}
		}
	}

	// Getters and setters omitted for brevity

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
}