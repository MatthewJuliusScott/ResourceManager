/*
 * 
 */
package com.resourcemanager.dao;

import java.time.LocalDate;
import java.util.List;

import com.resourcemanager.model.Allocation;

/**
 * The Interface AllocationDAO.
 */
public interface AllocationDAO {

	/**
	 * Adds the allocation.
	 *
	 * @param allocation the allocation
	 */
	public void addAllocation(Allocation allocation);

	/**
	 * Delete allocation.
	 *
	 * @param id the id
	 */
	public void deleteAllocation(Allocation allocation);

	/**
	 * Gets the allocation by ID.
	 *
	 * @param id the id
	 * @return the allocation by ID
	 */
	public Allocation getAllocationByID(Long id);

	/**
	 * List allocations.
	 *
	 * @return the list
	 */
	public List<Allocation> listAllocations();

	/**
	 * List required allocations.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list
	 */
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate);

	/**
	 * Update allocation.
	 *
	 * @param allocation the allocation
	 */
	public void updateAllocation(Allocation allocation);
}
