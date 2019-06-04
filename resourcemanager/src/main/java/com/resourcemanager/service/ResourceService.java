package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Resource;

public interface ResourceService {

	public void addResource(Resource p);

	public Resource getResourceById(Long id);

	public List<Resource> listResources();

	public void deleteResource(Long id);

	public void updateResource(Resource p);

}
