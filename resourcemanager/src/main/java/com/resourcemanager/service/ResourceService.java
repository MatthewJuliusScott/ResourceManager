package com.resourcemanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.resourcemanager.model.Resource;

public interface ResourceService {

	@Secured("ROLE_ADMIN")
	public void addResource(Resource p);

	@Secured("ROLE_ADMIN")
	public void deleteResource(Long id);

	@Secured("ROLE_ADMIN")
	public Resource getResourceById(Long id);

	@Secured("ROLE_ADMIN")
	public List<Resource> listResources();

	@Secured("ROLE_ADMIN")
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours);

	@Secured("ROLE_ADMIN")
	public void updateResource(Resource p);

}
