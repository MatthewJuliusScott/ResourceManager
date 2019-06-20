package com.resourcemanager.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Allocation;

public interface AllocationService {

	@Secured("ROLE_ADMIN")
	public void addAllocation(Allocation allocation);

	@Secured("ROLE_ADMIN")
	public void deleteAllocation(Long id);

	@Secured("ROLE_ADMIN")
	public Allocation getAllocationById(Long id);

	@Secured("ROLE_ADMIN")
	public List<Allocation> listAllocations();

	@Secured("ROLE_ADMIN")
	public void updateAllocation(Allocation allocation);
}
