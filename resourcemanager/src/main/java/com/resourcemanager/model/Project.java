
package com.resourcemanager.model;

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
 * The Class Project.
 */
@Entity
@Table(name = "PROJECT")
public class Project {

	/** The id. */
	@Id
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int			id;

	/** The name. */
	private String		name;

	/** The bookings. */
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name = "resource_booking", joinColumns = {
	        @JoinColumn(name = "project_id")}, inverseJoinColumns = {
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

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
