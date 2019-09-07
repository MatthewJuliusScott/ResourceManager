package com.resourcemanager.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.impl.AllocationDAOImpl;
import com.resourcemanager.model.Allocation;
import com.resourcemanager.model.Project;
import com.resourcemanager.model.Resource;
import com.resourcemanager.model.Skill;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AllocationServiceImplTest {

	@Autowired
	AllocationDAOImpl allocationDAO;

	@Test
	public void givenRepository_whenAddAllocation_thenAllocationAdded() {
		Allocation allocation = new Allocation();
		allocation.setId(0L);
		allocation.setHours(1);
		Project project = new Project();
		project.setId(1L);
		allocation.setProject(project);
		Resource resource = new Resource();
		resource.setId(1L);
		allocation.setResource(resource);
		Skill skill = new Skill();
		skill.setId(1L);
		allocation.setSkill(skill);
		try {
			allocationDAO.addAllocation(allocation);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void givenRepository_whenDeleteAllocation_thenDeleteAllocation() {
		try {
			Allocation allocation = allocationDAO.getAllocationByID(1L);
			if (allocation != null) {
				allocationDAO.deleteAllocation(allocation);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void givenRepository_whenGetAllocationByID_thenReturnAllocation() {
		try {
			allocationDAO.getAllocationByID(1L);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void givenRepository_whenListAllocations_thenReturnAllocations() {
		List<Allocation> acutal = allocationDAO.listAllocations();
		assertThat(acutal).isNotNull();
	}

	@Test
	public void givenRepository_whenUpdateAllocation_thenAllocationUpdated() {
		Allocation allocation = allocationDAO.getAllocationByID(1L);
		if (allocation == null) {
			allocation = new Allocation();
			allocation.setId(1L);
			allocation.setHours(1);
			Project project = new Project();
			project.setId(1L);
			allocation.setProject(project);
			Resource resource = new Resource();
			resource.setId(1L);
			allocation.setResource(resource);
			Skill skill = new Skill();
			skill.setId(1L);
			allocation.setSkill(skill);
		}

		try {
			allocationDAO.updateAllocation(allocation);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}