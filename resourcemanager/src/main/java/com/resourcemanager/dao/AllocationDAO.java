package com.resourcemanager.dao;

import java.time.LocalDate;
import java.util.List;

import com.resourcemanager.model.Allocation;

public interface AllocationDAO {

	public void addAllocation(Allocation allocation);

	public void deleteAllocation(Long id);

	public Allocation getAllocationByID(Long id);

	public List<Allocation> listAllocations();

	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate);

	public void updateAllocation(Allocation allocation);
}
