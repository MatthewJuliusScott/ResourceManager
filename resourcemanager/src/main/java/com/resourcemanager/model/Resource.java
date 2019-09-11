/*
 *
 */

package com.resourcemanager.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 * The Class Resource.
 */
@Entity
@Table(name = "resource")
public class Resource implements Cloneable {

	/** The id. */
	@Id
	@Column(name = "resource_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long				id;

	/** The name. */
	private String				name;

	/** The skills. */
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "resource_skill", joinColumns = {
			@JoinColumn(name = "resource_id") },
		inverseJoinColumns = {
				@JoinColumn(name = "skill_id") })
	@OrderColumn(name = "order_col")
	private List<Skill>			skills		= new ArrayList<>();

	/** The allocations. */
	@OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Allocation>	allocations	= new ArrayList<>();

	/** The hours. */
	@Basic
	private int					hours;

	/**
	 * The user associated with this resource. Usually only for a user with ROLE_USER authority.
	 */
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "user_id", nullable = true)
	@OrderColumn(name = "order_col")
	private User				user;

	/**
	 * Instantiates a new resource.
	 */
	public Resource() {
		super();
	}

	/**
	 * Instantiates a new resource.
	 *
	 * @param name
	 *            the name
	 * @param hours
	 *            the hours
	 */
	public Resource(String name, int hours) {
		super();
		this.name = name;
		this.hours = hours;
	}

	/**
	 * Adds the allocation.
	 *
	 * @param allocation
	 *            the allocation
	 */
	public void addAllocation(Allocation allocation) {
		allocations.add(allocation);
	}

	/**
	 * Adds the skill.
	 *
	 * @param skill
	 *            the skill
	 */
	public void addSkill(Skill skill) {
		skills.add(skill);
		skill.getResources().add(this);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Resource clone = (Resource) super.clone();
		clone.allocations = new ArrayList<Allocation>();
		if (allocations != null) {
			for (Allocation allocation : allocations) {
				clone.getAllocations().add(allocation);
			}
		}
		clone.skills = new ArrayList<Skill>();
		if (skills != null) {
			for (Skill skill : skills) {
				clone.getSkills().add(skill);
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
		Resource other = (Resource) obj;
		if (allocations == null) {
			if (other.allocations != null) {
				return false;
			}
		} else if (!allocations.containsAll(other.allocations)) {
			return false;
		}
		if (hours != other.hours) {
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
		if (skills == null) {
			if (other.skills != null) {
				return false;
			}
		} else if (!skills.equals(other.skills)) {
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
	public List<Skill> getSkills() {
		return skills;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allocations == null) ? 0 : allocations.hashCode());
		result = prime * result + hours;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((skills == null) ? 0 : skills.hashCode());
		return result;
	}

	/**
	 * Pre remove.
	 */
	@PreRemove
	public void preRemove() {
		for (Skill skill : new ArrayList<Skill>(skills)) {
			removeSkill(skill);
		}
		for (Allocation allocation : new ArrayList<Allocation>(allocations)) {
			removeAllocation(allocation);
		}
		if (user != null) {
			user.setResource(null);
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

			if (i.getResource() != null && i.getResource().equals(this)
				&& i.equals(allocation)) {
				i.setResource(null);
				iterator.remove();
			}
		}
	}

	/**
	 * Removes the skill.
	 *
	 * @param skill
	 *            the skill
	 */
	public void removeSkill(Skill skill) {
		for (Iterator<Skill> iterator = skills.iterator(); iterator
			.hasNext();) {
			Skill i = iterator.next();
			for (Resource resource : i.getResources()) {
				if (resource.equals(this) && i.equals(skill)) {
					iterator.remove();
				}
			}
		}
		skill.getResources().remove(this);
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
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * Sets the user.
	 *
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", skills=" + skills + ", allocations=" + allocations + ", hours="
			+ hours + ", user=" + (user != null ? String.valueOf(user.getId()) : "null") + "]";
	}

}
