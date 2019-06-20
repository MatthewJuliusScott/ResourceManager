package com.resourcemanager.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.resourcemanager.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityTest {

	@Autowired
	private ProjectService service;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = {"USER", "ADMIN"})
	public void givenAuthenticated_whenCallServiceWithSecured_thenOk() {
		assertThat(service.listProjects()).isNotEmpty();
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void givenUnauthenticated_whenCallService_thenThrowsException() {
		service.listProjects();
	}

}
