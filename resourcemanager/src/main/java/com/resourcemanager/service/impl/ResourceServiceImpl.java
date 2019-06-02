package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.ResourceDAO;
import com.resourcemanager.model.Resource;
import com.resourcemanager.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDAO resourceDAO;

	@Override
	@Transactional
	public void addResource(Resource p) {
		this.resourceDAO.addResource(p);
	}

	@Override
	@Transactional
	public Resource getResourceById(Long id) {
		return this.resourceDAO.getResourceById(id);
	}

	@Override
	@Transactional
	public List<Resource> listResources() {
		return this.resourceDAO.listResources();
	}

	@Override
	@Transactional
	public void deleteResource(Long id) {
		this.resourceDAO.removeResource(id);
	}

	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	@Override
	@Transactional
	public void updateResource(Resource p) {
		this.resourceDAO.updateResource(p);
	}

}
