package com.resourcemanager.service;

import java.time.LocalDate;
import java.util.List;

import com.resourcemanager.model.Resource;

public interface ResourceService {

	public void addResource(Resource p);

	public void deleteResource(Long id);

	public Resource getResourceById(Long id);

	public List<Resource> listResources();

	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours);

	public void updateResource(Resource p);

}
