package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Resource;

public interface ResourceService {

	public void addResource(Resource p);

	public Resource getResourceById(int id);

	public List<Resource> listResources();

	public void deleteResource(int id);

	public void updateResource(Resource p);

}
