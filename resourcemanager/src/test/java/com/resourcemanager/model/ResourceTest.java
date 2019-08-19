package com.resourcemanager.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class ResourceTest {

	@Test
	public void testAddAllocation() throws Exception {
		Resource resource = new Resource();
		resource.setId(1L);
		resource.setHours(40);
		resource.setName("Test Resources");
		Allocation allocation = new Allocation();
		allocation.setId(1L);
		resource.addAllocation(allocation);

		ArrayList<Allocation> expected = new ArrayList<Allocation>();
		expected.add(allocation);
		ArrayList<Allocation> actual = (ArrayList<Allocation>) resource.getAllocations();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testClone() throws Exception {
		Resource expected = new Resource();
		expected.setId(1L);
		expected.setHours(40);
		expected.setName("Test Resources");
		List<Allocation> allocationsExpected = new ArrayList<Allocation>();
		Allocation allocationExpected = new Allocation();
		allocationExpected.setId(1L);
		allocationsExpected.add(allocationExpected);
		expected.setAllocations(allocationsExpected);

		Resource actual = (Resource) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		Long resourceId = new Random().nextLong();
		Resource expected = new Resource();
		expected.setId(resourceId);
		expected.setHours(40);
		expected.setName("Test Resources");
		List<Allocation> allocationsExpected = new ArrayList<Allocation>();
		Allocation allocationExpected = new Allocation();
		allocationExpected.setId(1L);
		allocationsExpected.add(allocationExpected);
		expected.setAllocations(allocationsExpected);

		Resource actual = new Resource();
		actual.setId(resourceId);
		actual.setHours(40);
		actual.setName("Test Resources");
		List<Allocation> allocationsActual = new ArrayList<Allocation>();
		Allocation allocationActual = new Allocation();
		allocationActual.setId(1L);
		allocationsActual.add(allocationActual);
		expected.setAllocations(allocationsActual);

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
		x.setHours(40);
		x.setName("Test Resources");
		List<Allocation> allocationsExpected = new ArrayList<Allocation>();
		Allocation allocationExpected = new Allocation();
		allocationExpected.setId(1L);
		allocationsExpected.add(allocationExpected);
		x.setAllocations(allocationsExpected);
		y.setId(1L);
		y.setHours(40);
		y.setName("Test Resources");
		List<Allocation> allocationsActual = new ArrayList<Allocation>();
		Allocation allocationActual = new Allocation();
		allocationActual.setId(1L);
		allocationsActual.add(allocationActual);
		y.setAllocations(allocationsActual);
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testRemoveAllocation() throws Exception {
		Resource resource = new Resource();
		resource.setId(1L);
		resource.setHours(40);
		resource.setName("Test Resources");
		Allocation allocation = new Allocation();
		allocation.setId(1L);
		resource.addAllocation(allocation);

		ArrayList<Allocation> expected = new ArrayList<Allocation>();
		expected.add(allocation);
		ArrayList<Allocation> actual = (ArrayList<Allocation>) resource.getAllocations();

		assertTrue(expected.equals(actual));

		expected = new ArrayList<Allocation>();
		expected.add(allocation);
		resource.removeAllocation(allocation);
		actual = (ArrayList<Allocation>) resource.getAllocations();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testResource() throws Exception {
		Resource resource = new Resource();
		assertTrue(resource != null);
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
