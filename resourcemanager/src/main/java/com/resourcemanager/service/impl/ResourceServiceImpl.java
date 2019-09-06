/*
 * 
 */
package com.resourcemanager.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.ResourceDAO;
import com.resourcemanager.model.Resource;
import com.resourcemanager.service.ResourceService;

/**
 * The Class ResourceServiceImpl.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	/** The resource DAO. */
	@Autowired
	private ResourceDAO resourceDAO;

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ResourceService#addResource(com.resourcemanager.model.Resource)
	 */
	@Override
	@Transactional
	public void addResource(Resource p) {
		this.resourceDAO.addResource(p);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ResourceService#deleteResource(java.lang.Long)
	 */
	@Override
	@Transactional
	public void deleteResource(Long id) {
		this.resourceDAO.removeResource(id);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ResourceService#getResourceByID(java.lang.Long)
	 */
	@Override
	@Transactional
	public Resource getResourceByID(Long id) {
		return this.resourceDAO.getResourceByID(id);
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ResourceService#listResources()
	 */
	@Override
	@Transactional
	public List<Resource> listResources() {
		return this.resourceDAO.listResources();
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ResourceService#searchResources(long, java.time.LocalDate, java.time.LocalDate, int)
	 */
	@Override
	@Transactional
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours) {
		return this.resourceDAO.searchResources(skillId, startDate, endDate, hours);
	}

	/**
	 * Sets the resource DAO.
	 *
	 * @param resourceDAO the new resource DAO
	 */
	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	/* (non-Javadoc)
	 * @see com.resourcemanager.service.ResourceService#updateResource(com.resourcemanager.model.Resource)
	 */
	@Override
	@Transactional
	public void updateResource(Resource p) {
		this.resourceDAO.updateResource(p);
	}

}
