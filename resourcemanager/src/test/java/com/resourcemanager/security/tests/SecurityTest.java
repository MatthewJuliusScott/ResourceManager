package com.resourcemanager.security.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.resourcemanager.service.ProjectService;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityTest {

	@Autowired
	private ProjectService	projectService;

	@Autowired
	private SkillService	skillService;

	@Autowired
	private ResourceService	resourceService;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenAuthenticated_whenCallProjectServiceWithSecured_thenOk() {
		assertThat(projectService.listProjects()).isNotEmpty();
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenAuthenticated_whenCallResourceServiceWithSecured_thenOk() {
		assertThat(resourceService.listResources()).isNotEmpty();
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenAuthenticated_whenCallSkillServiceWithSecured_thenOk() {
		assertThat(skillService.listSkills()).isNotEmpty();
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void givenUnauthenticated_whenCallProjectService_thenThrowsException() {
		projectService.listProjects();
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void givenUnauthenticated_whenCallResourceService_thenThrowsException() {
		resourceService.listResources();
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void givenUnauthenticated_whenCallSkillService_thenThrowsException() {
		skillService.listSkills();
	}

}
