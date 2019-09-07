package com.resourcemanager.functionality.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.resourcemanager.model.Project;
import com.resourcemanager.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringSecurityConfig.class)
@TestPropertySource(
	locations = "classpath:application-integrationtest.properties")
public class ProjectServiceTest {

	@Autowired
	private ProjectService projectService;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenAuthentication_whenAddProjectAndListProjects_thenAllProjectsContainsProject() throws Exception {

		Project expected = new Project("Test Project");
		projectService.addProject(expected);

		List<Project> projects = projectService.listProjects();

		assertTrue(projects.contains(expected));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenAuthenticationAndGetProject_whenUpdateProject_thenProjectModified() throws Exception {

		Project expected = projectService.getProjectById(1L);

		// prove first project name is not currently "Some other name"
		assertFalse(expected.getName().equals("Some other name"));

		// update the project name
		expected.setName("Some other name");
		projectService.updateProject(expected);

		// Assert the name has changed in the persistence layer
		Project actual = projectService.getProjectById(1L);
		assertEquals(expected, actual);
	}
}
