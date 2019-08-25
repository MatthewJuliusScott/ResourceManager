package com.resourcemanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Allocation;

public interface AllocationService {

	@Secured("ROLE_ADMIN")
	public void addAllocation(Allocation allocation);

	@Secured("ROLE_ADMIN")
	public void deleteAllocation(Long id);

	@Secured("ROLE_ADMIN")
	public Allocation getAllocationByID(Long id);

	@Secured("ROLE_USER")
	public List<Allocation> listAllocations();

	@Secured("ROLE_ADMIN")
	public List<Allocation> listRequiredAllocations(LocalDate startDate, LocalDate endDate);

	@Secured("ROLE_ADMIN")
	public void updateAllocation(Allocation allocation);
	
	@Secured("ROLE_USER")
	public void allocateUser(Allocation allocation);
}
