package com.resourcemanager.model;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class AllocationTest {

	@Test
	public void testAllocation() throws Exception {
		Allocation allocation = new Allocation();
		assertTrue(allocation != null);
	}

	@Test
	public void testAllocationString() throws Exception {
		Allocation allocation = new Allocation();
		allocation.setId(1L);
		assertTrue(allocation != null);
		assertTrue(allocation.getId() == 1L);
	}

	@Test
	public void testClone() throws Exception {
		Allocation expected = new Allocation();
		expected.setId(new Random().nextLong());

		Resource resource = new Resource();
		resource.setId(new Random().nextLong());

		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		Allocation actual = (Allocation) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		Resource resource = new Resource();

		Long resourceId = new Random().nextLong();
		resource.setId(resourceId);

		Long allocationId = new Random().nextLong();
		Allocation expected = new Allocation();
		expected.setId(allocationId);

		Allocation actual = new Allocation();
		actual.setId(allocationId);

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends Allocation> clazz = new Allocation().getClass();
		assertTrue(clazz.equals(Allocation.class));
	}

	@Test
	public void testGetId() throws Exception {
		Allocation allocation = new Allocation();
		Long id = new Random().nextLong();
		allocation.setId(id);

		assertTrue(Long.valueOf(allocation.getId()).equals(id));
	}

	@Test
	public void testHashCode() throws Exception {
		Allocation x = new Allocation();
		Allocation y = new Allocation();
		x.setId(1L);
		x.setHours(10);
		y.setId(1L);
		y.setHours(10);
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testSetId() throws Exception {
		Allocation allocation = new Allocation();
		Long id = new Random().nextLong();
		allocation.setId(id);

		assertTrue(Long.valueOf(allocation.getId()).equals(id));
	}

	@Test
	public void testToString() throws Exception {
		Allocation allocation = new Allocation();
		allocation.setId(1L);
		allocation.setHours(40);
		System.out.println(allocation);
		assertTrue(allocation.toString()
			.equals("Allocation [id=1, project=null, skill=null, startDate=null, endDate=null, hours=40, resource=null]"));
	}

}
