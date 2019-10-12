/*
 *
 */
package com.resourcemanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Allocation;

/**
 * Provides an abstract interface for a single service layer for Allocations. The services do all the logic and data manipulation
 * on the data model and pass the updated model to the persistence layer.
 */
public interface AllocationService {

	/**
	 * Persists a new Allocation in the data source.
	 *
	 * @param allocation
	 *            the allocation
	 */
	@Secured("ROLE_ADMIN")
	public void addAllocation(Allocation allocation);

	/**
	 * Removes the Allocation from the data source.
	 *
	 * @param id
	 *            the id
	 */
	@Secured("ROLE_ADMIN")
	public void deleteAllocation(Allocation allocation);

	/**
	 * Optionally retrieves an Allocation from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the allocation by ID
	 */
	@Secured("ROLE_USER")
	public Allocation getAllocationById(Long id);

	/**
	 * Retrieves all Allocations from the data source.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Allocation> listAllocations();

	/**
	 * Retrieves all Allocations from the data source that do not have an associated Resource.
	 *
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @return the list
	 */
	@Secured("ROLE_ADMIN")
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate);

	/**
	 * Updates an existing Allocation in the data source.
	 *
	 * @param allocation
	 *            the allocation
	 */
	@Secured("ROLE_USER")
	public void updateAllocation(Allocation allocation);
}
