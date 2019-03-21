package com.resourcemanager.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
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

import com.resourcemanager.dao.PersonDAO;
import com.resourcemanager.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	private static final Logger		logger	= LoggerFactory.getLogger(PersonDAOImpl.class);

	@Autowired
	private EntityManagerFactory	entityManagerFactory;

	@Override
	public void addPerson(Person person) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.persist(person);
		logger.info("Person saved successfully, Person Details=" + person);
	}

	@Override
	public Person getPersonById(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Person person = session.find(Person.class, id);
		return person;
	}

	@Override
	public List<Person> listPersons() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery criteria = builder.createQuery(Person.class);
		Root contactRoot = criteria.from(Person.class);
		criteria.select(contactRoot);
		List<Person> personsList = session.createQuery(criteria).getResultList();
		for (Person person : personsList) {
			logger.info("Person List::" + person);
		}
		return personsList;
	}

	@Override
	public void removePerson(int id) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Person person = session.find(Person.class, id);
		session.remove(person);
		logger.info("Person deleted successfully, person details=" + person);
	}

	@Override
	public void updatePerson(Person person) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.merge(person);
		logger.info("Person updated successfully, Person Details=" + person);
	}

}
