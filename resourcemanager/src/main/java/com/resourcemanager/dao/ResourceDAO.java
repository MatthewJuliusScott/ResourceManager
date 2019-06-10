package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Resource;

public interface ResourceDAO {

	public void addResource(Resource p);
	public void updateResource(Resource p);
	public List<Resource> listResources();
	public Resource getResourceById(Long id);
	public void removeResource(Long id);
}