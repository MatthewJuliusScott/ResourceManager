package com.resourcemanager.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resourcemanager.dao.ResourceDAO;
import com.resourcemanager.model.Resource;

@Repository
public class ResourceDAOImpl implements ResourceDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(ResourceDAOImpl.class);

	@Autowired
	private EntityManagerFactory	entityManager;

	@Override
	public void addResource(Resource resource) {
		getCurrentSession().persist(resource);
		logger.info("Resource saved successfully, Resource Details=" + resource);
	}

	protected Session getCurrentSession() {
		return entityManager.unwrap(SessionFactory.class).getCurrentSession();
	}

	protected SessionFactory getCurrentSessionFactory() {
		return entityManager.unwrap(SessionFactory.class);
	}

	@Override
	public Resource getResourceById(Long id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		logger.info("Resource retrieved successfully, resource details=" + resource);
		return resource;
	}

	@Override
	public List<Resource> listResources() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Resource> criteria = builder.createQuery(Resource.class);
		Root<Resource> contactRoot = criteria.from(Resource.class);
		criteria.select(contactRoot);
		List<Resource> resourcesList = getCurrentSession().createQuery(criteria).getResultList();
		for (Resource resource : resourcesList) {
			logger.info("Resource List::" + resource);
		}
		return resourcesList;
	}

	@Override
	public void removeResource(Long id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		getCurrentSession().remove(resource);
		logger.info("Resource deleted successfully, resource details=" + resource);
	}

	@Override
	public List<Resource> searchResources(long skillId, LocalDate startDate, LocalDate endDate, int hours) {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery<Resource> criteria = builder.createQuery(Resource.class);
		Root<Resource> contactRoot = criteria.from(Resource.class);
		criteria.select(contactRoot);
		List<Resource> resourcesList = getCurrentSession().createQuery(criteria).getResultList();
		for (Resource resource : resourcesList) {
			logger.info("Resource List::" + resource);
		}
		return resourcesList;
	}

	@Override
	public void updateResource(Resource resource) {
		getCurrentSession().merge(resource);
		logger.info("Resource updated successfully, Resource Details=" + resource);
	}

}
