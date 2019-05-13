package com.resourcemanager.dao.impl;

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
	
	protected Session getCurrentSession() {
        return entityManager.unwrap(SessionFactory.class).getCurrentSession();
    }
	
	protected SessionFactory getCurrentSessionFactory() {
        return entityManager.unwrap(SessionFactory.class);
    }

	@Autowired
    private EntityManagerFactory entityManager;

	@Override
	public void addResource(Resource resource) {
		getCurrentSession().persist(resource);
		logger.info("Resource saved successfully, Resource Details=" + resource);
	}

	@Override
	public Resource getResourceById(int id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		logger.info("Resource retrieved successfully, resource details=" + resource);
		return resource;
	}

	@Override
	public List<Resource> listResources() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Resource.class);
		Root contactRoot = criteria.from(Resource.class);
		criteria.select(contactRoot);
		List<Resource> resourcesList = getCurrentSession().createQuery(criteria).getResultList();
		for (Resource resource : resourcesList) {
			logger.info("Resource List::" + resource);
		}
		return resourcesList;
	}

	@Override
	public void removeResource(int id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		getCurrentSession().remove(resource);
		logger.info("Resource deleted successfully, resource details=" + resource);
	}

	@Override
	public void updateResource(Resource resource) {
		getCurrentSession().merge(resource);
		logger.info("Resource updated successfully, Resource Details=" + resource);
	}

}
