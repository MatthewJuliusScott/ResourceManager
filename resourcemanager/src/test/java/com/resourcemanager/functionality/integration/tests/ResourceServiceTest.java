package com.resourcemanager.functionality.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.resourcemanager.config.SpringSecurityConfig;
import com.resourcemanager.model.Allocation;
import com.resourcemanager.model.Project;
import com.resourcemanager.model.Resource;
import com.resourcemanager.model.Skill;
import com.resourcemanager.service.AllocationService;
import com.resourcemanager.service.ProjectService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringSecurityConfig.class)
@TestPropertySource(
	locations = "classpath:application-integrationtest.properties")
public class ResourceServiceTest {

	@Autowired
	private ResourceService		resourceService;

	@Autowired
	private SkillService		skillService;

	@Autowired
	private ProjectService		projectService;

	@Autowired
	private AllocationService	allocationService;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenAuthentication_whenAddResourceAndListResources_thenAllResourcesContainsResource() throws Exception {

		Resource expected = new Resource("Test Resource", 40);
		resourceService.addResource(expected);

		List<Resource> resources = resourceService.listResources();

		assertTrue(resources.contains(expected));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenAuthenticationAndGetResource_whenUpdateResource_thenResourceModified() throws Exception {

		Resource expected = resourceService.getResourceByID(1L);

		// prove first resource name is not currently "Some other name"
		assertFalse(expected.getName().equals("Some other name"));

		// udpate the resource name
		expected.setName("Some other name");
		resourceService.updateResource(expected);

		// Assert the name has changed in the persistence layer
		Resource actual = resourceService.getResourceByID(1L);
		assertEquals(expected, actual);
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	/* CCRD Alternate flow */
	public void givenOnlyAllocatedResourceWithMatchingSkillAndSufficientHours_whenFindResource_thenNoResourceReturned()
		throws Exception {

		Skill skill = skillService.getSkillByID(1L);

		Project project = projectService.getProjectByID(1L);

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusDays(7);
		final int hours = 40;

		// given only allocated resources with matching skill and sufficient hours
		List<Resource> expected = resourceService.searchResources(skill.getId(), startDate, endDate, hours);
		for (Resource resource : expected) {
			Allocation allocation = new Allocation();
			allocation.setStartDate(startDate);
			allocation.setEndDate(endDate);
			allocation.setResource(resource);
			allocation.setSkill(skill);
			allocation.setHours(hours);
			allocation.setProject(project);
			projectService.updateProject(project);
			resourceService.updateResource(resource);
			allocationService.updateAllocation(allocation);
		}

		// when find resources
		List<Resource> actual = resourceService.searchResources(skill.getId(), startDate, endDate, hours);

		// assert none of the allocated resources are returned
		for (Resource resource : expected) {
			assertFalse(actual.contains(resource));
		}

	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	/* CCRD */
	public void givenUnallocatedResourceWithMatchingSkillAndSufficientHours_whenFindResource_thenReturnResource()
		throws Exception {

		Resource expected = new Resource("Test Resource", 40);
		Skill skill = skillService.getSkillByID(1L);
		expected.addSkill(skill);
		resourceService.addResource(expected);
		final int hours = 40;

		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusDays(7);

		List<Resource> actual = resourceService.searchResources(skill.getId(), startDate, endDate, hours);

		assertTrue(actual.contains(expected));
	}
}
