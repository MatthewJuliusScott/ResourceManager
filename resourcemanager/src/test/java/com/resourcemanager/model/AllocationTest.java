package com.resourcemanager.model;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.Test;

public class AllocationTest {

	@Test
	public void testAllocation() throws Exception {
		Allocation allocation = new Allocation();
		assertTrue(allocation != null);
	}

	@Test
	public void testAllocationLongProjectSkillLocalDateLocalDateIntResource() throws Exception {
		Allocation allocation =
			new Allocation(1L, new Project(), new Skill(), LocalDate.now(), LocalDate.now().plusDays(7), 40, new Resource());
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
		expected.setId(1L);
		expected.setHours(40);
		expected.setStartDate(LocalDate.now());
		expected.setEndDate(LocalDate.now().plusDays(7));
		expected.setProject(new Project());
		expected.setResource(new Resource());
		expected.setSkill(new Skill());

		Allocation actual = (Allocation) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		Resource resource = new Resource();
		Long resourceId = new Random().nextLong();
		resource.setId(resourceId);

		Project project = new Project();
		Long projectId = new Random().nextLong();
		project.setId(projectId);

		Skill skill = new Skill();
		Long skillId = new Random().nextLong();
		skill.setId(skillId);

		Long allocationId = new Random().nextLong();
		Allocation expected = new Allocation();
		expected.setId(allocationId);
		expected.setHours(40);
		expected.setStartDate(LocalDate.now());
		expected.setEndDate(LocalDate.now().plusDays(7));
		expected.setProject(project);
		expected.setResource(resource);
		expected.setSkill(skill);

		Allocation actual = new Allocation();
		actual.setId(allocationId);
		actual.setHours(40);
		actual.setStartDate(LocalDate.now());
		actual.setEndDate(LocalDate.now().plusDays(7));
		actual.setProject(project);
		actual.setResource(resource);
		actual.setSkill(skill);

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends Allocation> clazz = new Allocation().getClass();
		assertTrue(clazz.equals(Allocation.class));
	}

	@Test
	public void testGetEndDate() throws Exception {
		Allocation allocation = new Allocation();
		LocalDate expected = LocalDate.now();
		allocation.setEndDate(expected);

		LocalDate actual = allocation.getEndDate();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetEndDateAsString() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Allocation allocation = new Allocation();
		String expected = LocalDate.now().format(formatter);
		allocation.setEndDate(LocalDate.now());

		String actual = allocation.getEndDateAsString();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetHours() throws Exception {
		Allocation allocation = new Allocation();
		Integer expected = 40;
		allocation.setHours(expected);

		Integer actual = allocation.getHours();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetId() throws Exception {
		Allocation allocation = new Allocation();
		Long id = new Random().nextLong();
		allocation.setId(id);

		assertTrue(Long.valueOf(allocation.getId()).equals(id));
	}

	@Test
	public void testGetProject() throws Exception {
		Allocation allocation = new Allocation();
		Project expected = new Project();
		expected.setId(1L);
		expected.setName("Test Project");
		allocation.setProject(expected);

		Project actual = allocation.getProject();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetResource() throws Exception {
		Allocation allocation = new Allocation();
		Resource expected = new Resource();
		expected.setId(1L);
		expected.setName("Test Resource");
		allocation.setResource(expected);

		Resource actual = allocation.getResource();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetSkill() throws Exception {
		Allocation allocation = new Allocation();
		Skill expected = new Skill();
		expected.setId(1L);
		expected.setName("Test Skill");
		allocation.setSkill(expected);

		Skill actual = allocation.getSkill();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetStartDate() throws Exception {
		Allocation allocation = new Allocation();
		LocalDate expected = LocalDate.now();
		allocation.setStartDate(expected);

		LocalDate actual = allocation.getStartDate();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetStartDateAsString() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Allocation allocation = new Allocation();
		String expected = LocalDate.now().format(formatter);
		allocation.setStartDate(LocalDate.now());

		String actual = allocation.getStartDateAsString();

		assertTrue(expected.equals(actual));
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
	public void testPreRemove() throws Exception {
		Allocation expected = new Allocation();

		Skill skill = new Skill();
		skill.setId(1L);
		skill.setName("Test Skill");
		expected.setSkill(skill);

		Resource resource = new Resource();
		resource.setId(1L);
		resource.setName("Test Resource");
		expected.setResource(resource);

		Project project = new Project();
		project.setId(1L);
		project.setName("Test Project");
		expected.setProject(project);

		expected.preRemove();

		Allocation actual = new Allocation();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testSetHours() throws Exception {
		Allocation allocation = new Allocation();
		Integer expected = 40;
		allocation.setHours(expected);

		Integer actual = allocation.getHours();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testSetId() throws Exception {
		Allocation allocation = new Allocation();
		Long id = new Random().nextLong();
		allocation.setId(id);
		assertTrue(Long.valueOf(allocation.getId()).equals(id));
	}

	@Test
	public void testSetProject() throws Exception {
		Allocation allocation = new Allocation();
		Project expected = new Project();
		expected.setId(1L);
		expected.setName("Test Project");
		allocation.setProject(expected);

		Project actual = allocation.getProject();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testSetResource() throws Exception {
		Allocation allocation = new Allocation();
		Resource expected = new Resource();
		expected.setId(1L);
		expected.setName("Test Resource");
		allocation.setResource(expected);

		Resource actual = allocation.getResource();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testSetSkill() throws Exception {
		Allocation allocation = new Allocation();
		Skill expected = new Skill();
		expected.setId(1L);
		expected.setName("Test Skill");
		allocation.setSkill(expected);

		Skill actual = allocation.getSkill();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testToString() throws Exception {
		Allocation allocation =
			new Allocation(1L, new Project(), new Skill(), LocalDate.now(), LocalDate.now().plusDays(7), 40, new Resource());
		assertTrue(allocation.toString()
			.startsWith("Allocation [id=1, project=Project [id=0, name=null], skill=Skill [id=0, name=null], startDate="));
		assertTrue(allocation.toString()
			.endsWith(", hours=40, resource=Resource [id=0, name=null, skills=[], allocations=[], hours=0]]"));
	}

}
