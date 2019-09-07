/*
 * 
 */
package com.resourcemanager.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.AllocationDAO;
import com.resourcemanager.model.Allocation;
import com.resourcemanager.service.AllocationService;

/**
 * The Class AllocationServiceImpl.
 */
@Service
public class AllocationServiceImpl implements AllocationService {

	/** The allocation DAO. */
	@Autowired
	private AllocationDAO allocationDAO;

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.AllocationService#addAllocation(com.resourcemanager.model.Allocation)
	 */
	@Override
	@Transactional
	public void addAllocation(Allocation allocation) {
		this.allocationDAO.addAllocation(allocation);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.AllocationService#deleteAllocation(java.lang.Long)
	 */
	@Override
	@Transactional
	public void deleteAllocation(Allocation allocation) {
		this.allocationDAO.deleteAllocation(allocation);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.AllocationService#getAllocationByID(java.lang.Long)
	 */
	@Override
	@Transactional
	public Allocation getAllocationById(Long id) {
		return this.allocationDAO.getAllocationByID(id);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.AllocationService#listAllocations()
	 */
	@Override
	@Transactional
	public List<Allocation> listAllocations() {
		return this.allocationDAO.listAllocations();
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.AllocationService#listRequiredAllocations(java.time.LocalDate, java.time.LocalDate)
	 */
	@Override
	@Transactional
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate) {
		return this.allocationDAO.listRequiredAllocations(startDate, endDate);
	}

	/**
	 * Sets the allocation DAO.
	 *
	 * @param allocationDAO the new allocation DAO
	 */
	public void setAllocationDAO(AllocationDAO allocationDAO) {
		this.allocationDAO = allocationDAO;
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.AllocationService#updateAllocation(com.resourcemanager.model.Allocation)
	 */
	@Override
	@Transactional
	public void updateAllocation(Allocation allocation) {
		this.allocationDAO.updateAllocation(allocation);
	}
	
	/* (non-Javadoc)
	 * @see com.resourcemanager.service.AllocationService#allocateUser(com.resourcemanager.model.Allocation)
	 */
	@Override
	@Transactional
	public void allocateUser(Allocation allocation) {
		this.allocationDAO.updateAllocation(allocation);
	}

}
