package com.resourcemanager.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.impl.SkillDAOImpl;
import com.resourcemanager.model.Skill;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillServiceImplTest {

	@Autowired
	SkillDAOImpl skillDAO;

	@Test
	public void givenRepository_whenAddSkill_thenSkillAdded() throws Exception {
		Skill expected = new Skill();
		expected.setId(0L);
		expected.setName("Test Skill");

		skillDAO.addSkill(expected);
		Skill actual = skillDAO.getSkillByName("Test Skill");
		assertEquals(expected, actual);
	}

	@Test
	public void givenRepository_whenDeleteSkill_thenSkillDeleted() throws Exception {
		Skill expected = new Skill();
		expected.setId(0L);
		expected.setName("Test Skill");

		skillDAO.addSkill(expected);
		Skill actual = skillDAO.getSkillByName("Test Skill");
		assertEquals(expected, actual);
		skillDAO.deleteSkill(actual.getId());
		Skill skill = skillDAO.getSkillByName("Test Skill");
		assertNull(skill);
	}

	@Test
	public void givenRepository_whenGetSkillById_thenSkillReturned() throws Exception {
		Skill skill = skillDAO.getSkillById(1L);
		assertNotNull(skill);
	}

	@Test
	public void givenRepository_whenListSkill_thenSkillsReturned() throws Exception {
		List<Skill> skills = skillDAO.listSkills();
		assertNotNull(skills);
		assertTrue(skills.size() > 0);
	}

	@Test
	public void givenRepository_whenUpdateSkill_thenSkillUpdated() throws Exception {
		Skill expected = skillDAO.getSkillById(1L);
		expected.setName("Some Name");
		skillDAO.updateSkill(expected);
		assertFalse(expected.getName().equals("Another Name"));
		expected.setName("Another Name");
		skillDAO.updateSkill(expected);
		Skill actual = skillDAO.getSkillById(1L);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends SkillServiceImpl> clazz = new SkillServiceImpl().getClass();
		assertTrue(clazz.equals(SkillServiceImpl.class));
	}

}