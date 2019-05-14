
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
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/**
 * The Class Project.
 */
@Entity
@Table(name = "project")
public class Project {

	/** The id. */
	@Id
	@Column(name = "project_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int				id;

	/** The name. */
	private String			name;

	/** The allocations. */
	@ManyToOne(optional = true, cascade = { CascadeType.MERGE })
	@JoinTable(name = "project_resource_allocation", joinColumns = {
			@JoinColumn(columnDefinition = "integer", name = "project_id", nullable = true) },
		inverseJoinColumns = {
				@JoinColumn(columnDefinition = "integer", name = "allocation_id", nullable = true) })
	@OrderColumn(name = "order_col")
	@Embedded
	private Allocation[]	allocations;

	/**
	 * Gets the allocations.
	 *
	 * @return the allocations
	 */
	public Allocation[] getAllocations() {
		return allocations;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the allocations.
	 *
	 * @param allocations
	 *            the allocations to set
	 */
	public void setAllocations(Allocation[] allocations) {
		this.allocations = allocations;
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

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
