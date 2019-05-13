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
	public void addPerson(Resource resource) {
		getCurrentSession().persist(resource);
		logger.info("Person saved successfully, Person Details=" + resource);
	}

	@Override
	public Resource getPersonById(int id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		logger.info("Person retrieved successfully, resource details=" + resource);
		return resource;
	}

	@Override
	public List<Resource> listPersons() {
		CriteriaBuilder builder = getCurrentSessionFactory().getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Resource.class);
		Root contactRoot = criteria.from(Resource.class);
		criteria.select(contactRoot);
		List<Resource> resourcesList = getCurrentSession().createQuery(criteria).getResultList();
		for (Resource resource : resourcesList) {
			logger.info("Person List::" + resource);
		}
		return resourcesList;
	}

	@Override
	public void removePerson(int id) {
		Resource resource = getCurrentSession().find(Resource.class, id);
		getCurrentSession().remove(resource);
		logger.info("Person deleted successfully, resource details=" + resource);
	}

	@Override
	public void updatePerson(Resource resource) {
		getCurrentSession().merge(resource);
		logger.info("Person updated successfully, Person Details=" + resource);
	}

}
