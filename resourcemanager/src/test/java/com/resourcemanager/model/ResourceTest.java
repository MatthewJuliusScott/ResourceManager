package com.resourcemanager.model;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class ResourceTest {

	@Test
	public void testClone() throws Exception {
		Resource expected = new Resource();
		expected.setId(new Random().nextLong());

		Resource actual = (Resource) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		Long resourceId = new Random().nextLong();
		Resource expected = new Resource();
		expected.setId(resourceId);

		Resource actual = new Resource();
		actual.setId(resourceId);

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends Resource> clazz = new Resource().getClass();
		assertTrue(clazz.equals(Resource.class));
	}

	@Test
	public void testGetId() throws Exception {
		Resource resource = new Resource();
		Long id = new Random().nextLong();
		resource.setId(id);

		assertTrue(Long.valueOf(resource.getId()).equals(id));
	}

	@Test
	public void testHashCode() throws Exception {
		Resource x = new Resource();
		Resource y = new Resource();
		x.setId(1L);
		x.setHours(10);
		y.setId(1L);
		y.setHours(10);
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testResource() throws Exception {
		Resource resource = new Resource();
		assertTrue(resource != null);
	}

	@Test
	public void testResourceString() throws Exception {
		Resource resource = new Resource();
		resource.setId(1L);
		assertTrue(resource != null);
		assertTrue(resource.getId() == 1L);
	}

	@Test
	public void testSetId() throws Exception {
		Resource resource = new Resource();
		Long id = new Random().nextLong();
		resource.setId(id);

		assertTrue(Long.valueOf(resource.getId()).equals(id));
	}

	@Test
	public void testToString() throws Exception {
		Resource resource = new Resource();
		resource.setId(1L);
		resource.setHours(40);
		System.out.println(resource);
		assertTrue(resource.toString()
			.equals("Resource [id=1, name=null, skills=[], allocations=[], hours=40]"));
	}

}
