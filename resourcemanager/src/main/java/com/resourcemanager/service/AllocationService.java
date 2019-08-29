/*
 * 
 */
package com.resourcemanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Allocation;

/**
 * The Interface AllocationService.
 */
public interface AllocationService {

	/**
	 * Adds the allocation.
	 *
	 * @param allocation the allocation
	 */
	@Secured("ROLE_ADMIN")
	public void addAllocation(Allocation allocation);

	/**
	 * Delete allocation.
	 *
	 * @param id the id
	 */
	@Secured("ROLE_ADMIN")
	public void deleteAllocation(Long id);

	/**
	 * Gets the allocation by ID.
	 *
	 * @param id the id
	 * @return the allocation by ID
	 */
	@Secured("ROLE_ADMIN")
	public Allocation getAllocationByID(Long id);

	/**
	 * List allocations.
	 *
	 * @return the list
	 */
	@Secured("ROLE_USER")
	public List<Allocation> listAllocations();

	/**
	 * List required allocations.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list
	 */
	@Secured("ROLE_ADMIN")
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate);

	/**
	 * Update allocation.
	 *
	 * @param allocation the allocation
	 */
	@Secured("ROLE_ADMIN")
	public void updateAllocation(Allocation allocation);
	
	/**
	 * Allocate user.
	 *
	 * @param allocation the allocation
	 */
	@Secured("ROLE_USER")
	public void allocateUser(Allocation allocation);
}
