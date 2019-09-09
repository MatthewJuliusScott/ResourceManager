/*
 * 
 */

package com.resourcemanager.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

/**
 * The Class Skill.
 */
@Entity(name = "Skill")
@Table(name = "skill")
@NaturalIdCache
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "skill_id")) })
public class Skill implements Cloneable {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long				id;

	/** The name. */
	private String				name;

	/** The resources. */
	@ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Resource>		resources	= new ArrayList<Resource>();

	/** The allocations. */
	@ManyToMany(mappedBy = "project", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Allocation>	allocations	= new ArrayList<>();

	/**
	 * Instantiates a new skill.
	 */
	public Skill() {
		super();
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
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Skill clone = (Skill) super.clone();
		clone.allocations = new ArrayList<Allocation>();
		if (allocations != null) {
			for (Allocation allocation : allocations) {
				clone.getAllocations().add(allocation);
			}
		}
		clone.resources = new ArrayList<Resource>();
		if (resources != null) {
			for (Resource resource : resources) {
				clone.getResources().add(resource);
			}
		}
		return clone;
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
	 * Gets the allocations.
	 *
	 * @return the allocations
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
	 * Pre remove.
	 */
	@PreRemove
	public void preRemove() {
		for (Resource resource : new ArrayList<Resource>(resources)) {
			removeResource(resource);
		}
		for (Allocation allocation : new ArrayList<Allocation>(allocations)) {
			removeAllocation(allocation);
		}
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

			if (i.getSkill().equals(this) && i.equals(allocation)) {
				allocation.setSkill(null);
				iterator.remove();
			}
		}
	}

	/**
	 * Removes the resource.
	 *
	 * @param resource
	 *            the resource
	 */
	private void removeResource(Resource resource) {
		for (Resource r : resources) {
			for (Iterator<Skill> is = r.getSkills().iterator(); is.hasNext();) {
				Skill skill = is.next();
				if (skill.equals(this) && r.equals(resource)) {
					is.remove();
				}
			}
		}
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + "]";
	}
}