package com.resourcemanager.dao;

import java.util.List;

import com.resourcemanager.model.Resource;

public interface ResourceDAO {

	public void addPerson(Resource p);
	public void updatePerson(Resource p);
	public List<Resource> listPersons();
	public Resource getPersonById(int id);
	public void removePerson(int id);
}
