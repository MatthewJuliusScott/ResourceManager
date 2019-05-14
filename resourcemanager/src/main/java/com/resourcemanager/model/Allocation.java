
package com.resourcemanager.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Class Allocation. Represents a period of time for which something can be reserved.
 */
@Entity
@Table(name = "ALLOCATION")
@Embeddable
public class Allocation {

	/** The allocation id. */
	@Id
	@Column(name = "allocation_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int			id;

	/** The start date. */
	@Basic
	private LocalDate	startDate;

	/** The end date. */
	@Basic
	private LocalDate	endDate;

	/** The skill. */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skill_id")
	private Skill		skill;

	/** The resource. */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_id")
	private Resource	resource;

	/** The hours. */
	@Basic
	private int			hours;

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
		if (!(obj instanceof Allocation)) {
			return false;
		}
		Allocation other = (Allocation) obj;
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!endDate.equals(other.endDate)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
			return false;
		}
		return true;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public Resource getResource() {
		return resource;
	}

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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result
			+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
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
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

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
		return "Allocation [id=" + id + ", startDate=" + startDate + ", endDate="
			+ endDate + "]";
	}

}
