package com.resourcemanager.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resourcemanager.model.Resource;

@Repository
public interface ResourceDAO {

	public void addResource(Resource p);

	public Resource getResourceByID(Long id);

	public List<Resource> listResources();

	public void removeResource(Long id);

	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours);

	public void updateResource(Resource p);
}
