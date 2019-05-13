
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
import javax.persistence.OneToMany;
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
	private int		id;

	/** The name. */
	private String	name;
	
	/** The bookings. */
	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(
		name = "person_booking",
		joinColumns = { @JoinColumn(name = "project_id") },
		inverseJoinColumns = { @JoinColumn(name = "booking_id") })
	@OrderColumn(name = "order_col")
	@Embedded
	private Booking[]	bookings;

	
	/**
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
	 * @param bookings the bookings to set
	 */
	public void setBookings(Booking[] bookings) {
		this.bookings = bookings;
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
}
