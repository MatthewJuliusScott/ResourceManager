package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Allocation;

public interface AllocationDAO {

	public void addAllocation(Allocation allocation);

	public void deleteAllocation(int id);

	public Allocation getAllocationById(int id);

	public List<Allocation> listAllocations();

	public void updateAllocation(Allocation allocation);
}
