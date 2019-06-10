package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Allocation;

public interface AllocationService {

	public void addAllocation(Allocation allocation);

	public void deleteAllocation(Long id);

	public Allocation getAllocationById(Long id);

	public List<Allocation> listAllocations();

	public void updateAllocation(Allocation allocation);
}