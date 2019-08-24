package com.resourcemanager.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.AllocationDAO;
import com.resourcemanager.model.Allocation;
import com.resourcemanager.service.AllocationService;

@Service
public class AllocationServiceImpl implements AllocationService {

	@Autowired
	private AllocationDAO allocationDAO;

	@Override
	@Transactional
	public void addAllocation(Allocation allocation) {
		this.allocationDAO.addAllocation(allocation);
	}

	@Override
	@Transactional
	public void deleteAllocation(Long id) {
		this.allocationDAO.deleteAllocation(id);
	}

	@Override
	@Transactional
	public Allocation getAllocationByID(Long id) {
		return this.allocationDAO.getAllocationByID(id);
	}

	@Override
	@Transactional
	public List<Allocation> listAllocations() {
		return this.allocationDAO.listAllocations();
	}

	@Override
	@Transactional
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate) {
		return this.allocationDAO.listRequiredAllocations(startDate, endDate);
	}

	public void setAllocationDAO(AllocationDAO allocationDAO) {
		this.allocationDAO = allocationDAO;
	}

	@Override
	@Transactional
	public void updateAllocation(Allocation allocation) {
		this.allocationDAO.updateAllocation(allocation);
	}
	
	@Override
	@Transactional
	public void allocateUser(Allocation allocation) {
		this.allocationDAO.updateAllocation(allocation);
	}

}
