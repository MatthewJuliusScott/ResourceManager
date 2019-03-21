package com.resourcemanager.service;

import java.util.List;

import com.resourcemanager.model.Person;

public interface PersonService {

	public void addPerson(Person p);

	public Person getPersonById(int id);

	public List<Person> listPersons();

	public void removePerson(int id);

	public void updatePerson(Person p);

}
