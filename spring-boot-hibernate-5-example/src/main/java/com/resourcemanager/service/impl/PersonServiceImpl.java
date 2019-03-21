package com.resourcemanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.PersonDAO;
import com.resourcemanager.model.Person;
import com.resourcemanager.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;

	@Override
	@Transactional
	public void addPerson(Person p) {
		this.personDAO.addPerson(p);
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		return this.personDAO.getPersonById(id);
	}

	@Override
	@Transactional
	public List<Person> listPersons() {
		return this.personDAO.listPersons();
	}

	@Override
	@Transactional
	public void removePerson(int id) {
		this.personDAO.removePerson(id);
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	@Override
	@Transactional
	public void updatePerson(Person p) {
		this.personDAO.updatePerson(p);
	}

}
