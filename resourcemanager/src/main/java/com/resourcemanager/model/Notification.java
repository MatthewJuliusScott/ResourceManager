/*
 *
 */

package com.resourcemanager.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalIdCache;

/**
 * Notification data model. Represents a message for a user, created on a specific date and time, and whether or not it has been
 * seen or not.
 */
@Entity(name = "Notification")
@Table(name = "notification")
@NaturalIdCache
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "notification_id")) })
public class Notification implements Cloneable {

	/** The Constant formatter. */
	public static final DateTimeFormatter	formatter	= DateTimeFormatter
		.ofPattern("dd/MM/yyyy");

	/** The id. */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long							id			= 0;

	/** The message. */
	@Column
	private String							message		= "";

	/** The start date. */
	@Column
	private LocalDate						createdDate	= LocalDate.now();

	/** The seen. */
	@Column
	private boolean							seen		= false;

	/**
	 * Instantiates a new notification.
	 */
	public Notification() {
		super();
	}

	/**
	 * Instantiates a new notification.
	 *
	 * @param message
	 *            the message
	 */
	public Notification(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Notification clone = (Notification) super.clone();
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
		Notification other = (Notification) obj;
		if (id != other.id) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the createdDate formatted as a String
	 */
	public String getCreatedDateAsString() {
		return createdDate.format(formatter);
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
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
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
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	/**
	 * Checks if is seen.
	 *
	 * @return true, if is seen
	 */
	public boolean isSeen() {
		return seen;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the new created date
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
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
	 * Sets the message.
	 *
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Sets the seen.
	 *
	 * @param seen
	 *            the new seen
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Notification [id=" + id + ", message=" + message + "]";
	}
}