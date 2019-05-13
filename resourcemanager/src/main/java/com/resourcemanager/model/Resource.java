
package com.resourcemanager.model;

import java.util.Arrays;

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
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * The Class Resource.
 */
@Entity
@Table(name = "RESOURCE")
public class Resource {

	/** The id. */
	@Id
	@Column(name = "resource_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int		id;

	/** The name. */
	private String	name;

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

	/** The skills. */
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "resource_skill", joinColumns = {
	        @JoinColumn(name = "resource_id")}, inverseJoinColumns = {
	                @JoinColumn(name = "skill_id")})
	@OrderColumn(name = "order_col")
	@Embedded
	private Skill[]		skills;

	/** The bookings. */
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "resource_booking", joinColumns = {
	        @JoinColumn(name = "resource_id")}, inverseJoinColumns = {
	                @JoinColumn(name = "booking_id")})
	@OrderColumn(name = "order_col")
	@Embedded
	private Booking[]	bookings;

	/**
	 * Gets the bookings.
	 *
	 * @return the bookings
	 */
	public Booking[] getBookings() {
		return bookings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", skills="
		        + Arrays.toString(skills) + ", bookings="
		        + Arrays.toString(bookings) + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bookings);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(skills);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (!Arrays.equals(bookings, other.bookings)) {
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public Skill[] getSkills() {
		return skills;
	}

	/**
	 * Sets the bookings.
	 *
	 * @param bookings the bookings to set
	 */
	public void setBookings(Booking[] bookings) {
		this.bookings = bookings;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}
}
