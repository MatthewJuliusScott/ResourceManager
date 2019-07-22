package com.resourcemanager.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;

public class ProjectTest {

	@Test
	public void testAddAllocation() throws Exception {
		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		List<Allocation> expected = new ArrayList<Allocation>();
		expected.add(allocation);
		Project project = new Project();
		project.addAllocation(allocation);

		List<Allocation> actual = project.getAllocations();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testClone() throws Exception {
		Project expected = new Project();
		expected.setId(new Random().nextLong());
		expected.setName(UUID.randomUUID().toString());

		Resource resource = new Resource();
		resource.setId(new Random().nextLong());

		Skill skill = new Skill();
		skill.setId(new Random().nextLong());
		skill.setName(UUID.randomUUID().toString());

		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());
		allocation.setSkill(skill);
		skill.getAllocations().add(allocation);
		expected.getAllocations().add(allocation);

		Project actual = (Project) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		Project expected = new Project();
		Long id = new Random().nextLong();
		expected.setId(id);
		String name = UUID.randomUUID().toString();
		expected.setName(name);

		Resource resource = new Resource();
		resource.setId(new Random().nextLong());

		Skill skill = new Skill();
		skill.setId(new Random().nextLong());
		skill.setName(UUID.randomUUID().toString());

		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());
		allocation.setSkill(skill);
		skill.getAllocations().add(allocation);
		expected.getAllocations().add(allocation);

		Project actual = new Project();
		actual.setId(id);
		actual.setName(name);

		actual.getAllocations().add(allocation);
	}

	@Test
	public void testGetAllocations() throws Exception {
		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		List<Allocation> expected = new ArrayList<Allocation>();
		expected.add(allocation);
		Project project = new Project();
		project.addAllocation(allocation);

		List<Allocation> actual = project.getAllocations();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends Project> clazz = new Project().getClass();
		assertTrue(clazz.equals(Project.class));
	}

	@Test
	public void testGetId() throws Exception {
		Project project = new Project();
		Long id = new Random().nextLong();
		project.setId(id);

		assertTrue(Long.valueOf(project.getId()).equals(id));
	}

	@Test
	public void testGetName() throws Exception {
		Skill skill = new Skill();
		String name = UUID.randomUUID().toString();
		skill.setName(name);

		assertTrue(skill.getName().equals(name));
	}

	@Test
	public void testHashCode() throws Exception {
		Project x = new Project("Test Project");
		Project y = new Project("Test Project");
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testProject() throws Exception {
		Project project = new Project();
		assertTrue(project != null);
	}

	@Test
	public void testProjectString() throws Exception {
		Project project = new Project("Test Project");
		assertTrue(project != null);
	}

	@Test
	public void testRemoveAllocation() throws Exception {
		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		List<Allocation> expected = new ArrayList<Allocation>();
		Project project = new Project();
		allocation.setProject(project);
		project.addAllocation(allocation);
		project.removeAllocation(allocation);

		List<Allocation> actual = project.getAllocations();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testSetAllocations() throws Exception {
		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		List<Allocation> expected = new ArrayList<Allocation>();
		expected.add(allocation);
		Project project = new Project();
		project.setAllocations(expected);

		List<Allocation> actual = project.getAllocations();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testSetId() throws Exception {
		Project project = new Project();
		Long id = new Random().nextLong();
		project.setId(id);

		assertTrue(Long.valueOf(project.getId()).equals(id));
	}

	@Test
	public void testSetName() throws Exception {
		Project project = new Project();
		String name = UUID.randomUUID().toString();
		project.setName(name);

		assertTrue(project.getName().equals(name));
	}

	@Test
	public void testToString() throws Exception {
		Project project = new Project();
		Long id = 100L;
		project.setId(id);
		String name = "Test Project";
		project.setName(name);

		Resource resource = new Resource();
		resource.setId(new Random().nextLong());

		Skill skill = new Skill();
		skill.setId(new Random().nextLong());
		skill.setName(UUID.randomUUID().toString());

		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());
		allocation.setSkill(skill);
		skill.getAllocations().add(allocation);
		project.getAllocations().add(allocation);

		assertTrue(project.toString().equals("Project [id=100, name=Test Project]"));
	}
}
