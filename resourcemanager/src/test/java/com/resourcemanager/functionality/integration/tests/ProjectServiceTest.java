package com.resourcemanager.functionality.integration.tests;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.resourcemanager.config.SpringSecurityConfig;
import com.resourcemanager.model.Project;
import com.resourcemanager.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringSecurityConfig.class)
public class ProjectServiceTest {

	@Autowired
	private WebApplicationContext	context;

	private MockMvc					mvc;

	@MockBean
	private ProjectService			projectService;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenProjectManager_whenCreateProject_thenReturnProject() throws Exception {

		Project expected = new Project("Test Project");

		List<Project> allProjects = Arrays.asList(expected);

		given(projectService.listProjects()).willReturn(allProjects);

		MvcResult result = mvc.perform(get("/projects")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andReturn();

		String content = result.getResponse().getContentAsString();
		System.out.println("This is the content: " + content);

	}

	@Before
	public void setup() {
		mvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(springSecurity())
			.build();
	}

}
