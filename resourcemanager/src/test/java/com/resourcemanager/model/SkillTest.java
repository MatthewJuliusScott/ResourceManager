package com.resourcemanager.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;

public class SkillTest {

	@Test
	public void testClone() throws Exception {
		Skill expected = new Skill();
		expected.setId(new Random().nextLong());
		expected.setName(UUID.randomUUID().toString());

		Resource resource = new Resource();
		resource.setId(new Random().nextLong());

		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		expected.getResources().add(resource);
		allocation.setSkill(expected);
		expected.getAllocations().add(allocation);

		Skill actual = (Skill) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		Resource resource = new Resource();

		Long resourceId = new Random().nextLong();
		resource.setId(resourceId);

		Allocation allocation = new Allocation();
		Long allocationId = new Random().nextLong();
		allocation.setId(allocationId);

		Long skillId = new Random().nextLong();
		Skill expected = new Skill();
		expected.setId(skillId);
		expected.getResources().add(resource);
		allocation.setSkill(expected);
		expected.getAllocations().add(allocation);

		Skill actual = new Skill();
		actual.setId(skillId);
		actual.getResources().add(resource);
		allocation.setSkill(actual);
		actual.getAllocations().add(allocation);

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends Skill> clazz = new Skill().getClass();
		assertTrue(clazz.equals(Skill.class));
	}

	@Test
	public void testGetId() throws Exception {
		Skill skill = new Skill();
		Long id = new Random().nextLong();
		skill.setId(id);

		assertTrue(Long.valueOf(skill.getId()).equals(id));
	}

	@Test
	public void testGetName() throws Exception {
		Skill skill = new Skill();
		String name = UUID.randomUUID().toString();
		skill.setName(name);

		assertTrue(skill.getName().equals(name));
	}

	@Test
	public void testGetResources() throws Exception {
		Resource resource = new Resource();
		resource.setId(new Random().nextLong());
		Skill skill = new Skill();
		skill.getResources().add(resource);

		List<Resource> expected = new ArrayList<Resource>();
		expected.add(resource);

		List<Resource> actual = skill.getResources();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testHashCode() throws Exception {
		Skill x = new Skill("Test Skill");
		Skill y = new Skill("Test Skill");
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testPreRemove() throws Exception {
		Resource resource = new Resource();
		resource.setId(new Random().nextLong());

		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		Skill skill = new Skill();
		skill.getResources().add(resource);
		allocation.setSkill(skill);
		skill.getAllocations().add(allocation);

		skill.preRemove();

		assertTrue(!resource.getSkills().contains(skill));
		assertTrue(allocation.getSkill() == null);
	}

	@Test
	public void testRemoveAllocation() throws Exception {
		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		Skill skill = new Skill();
		allocation.setSkill(skill);
		skill.getAllocations().add(allocation);

		skill.removeAllocation(allocation);

		assertTrue(!skill.getAllocations().contains(allocation));
	}

	@Test
	public void testSetId() throws Exception {
		Skill skill = new Skill();
		Long id = new Random().nextLong();
		skill.setId(id);

		assertTrue(Long.valueOf(skill.getId()).equals(id));
	}

	@Test
	public void testSetName() throws Exception {
		Skill skill = new Skill();
		String name = UUID.randomUUID().toString();
		skill.setName(name);

		assertTrue(skill.getName().equals(name));
	}

	@Test
	public void testSkill() throws Exception {
		Skill skill = new Skill();
		assertTrue(skill != null);
	}

	@Test
	public void testSkillString() throws Exception {
		Skill skill = new Skill("Test Skill");
		assertTrue(skill != null);
		assertTrue(skill.getName().equals("Test Skill"));
	}

	@Test
	public void testToString() throws Exception {
		Resource resource = new Resource();
		resource.setId(new Random().nextLong());

		Allocation allocation = new Allocation();
		allocation.setId(new Random().nextLong());

		Skill skill = new Skill();
		skill.getResources().add(resource);
		allocation.setSkill(skill);
		skill.getAllocations().add(allocation);

		skill.setName("Test Skill");

		assertTrue(skill.toString().equals("Skill [id=0, name=Test Skill]"));
	}

}
