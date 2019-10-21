/*
 *
 */
package com.resourcemanager.dao;

import java.time.LocalDate;
import java.util.List;

import com.resourcemanager.model.Allocation;

/**
 * Allocation Data Access Object provides an abstract interface to some type of database or other persistence mechanism. It maps
 * application calls to the persistence layer, the DAO provides some specific data operations without exposing details of the
 * database. This isolation supports the single responsibility principle. It separates what data access the application needs, in
 * terms of domain-specific objects and data types (the public interface of the DAO), from how these needs can be satisfied with a
 * specific DBMS, database schema, etc. (the implementation of the DAO).
 */
public interface AllocationDAO {

	/**
	 * Persists a new Allocation in the data source.
	 *
	 * @param allocation
	 *            the allocation
	 */
	public void addAllocation(Allocation allocation);

	/**
	 * Removes the Allocation from the data source.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteAllocation(Allocation allocation);

	/**
	 * Optionally retrieves an Allocation from the data source by Id if it exists.
	 *
	 * @param id
	 *            the id
	 * @return the allocation by ID
	 */
	public Allocation getAllocationByID(Long id);

	/**
	 * Retrieves all Allocations from the data source.
	 *
	 * @return the list
	 */
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
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate);

	/**
	 * Updates an existing Allocation in the data source.
	 *
	 * @param allocation
	 *            the allocation
	 */
	public void updateAllocation(Allocation allocation);
}
