package com.resourcemanager.functionality.unit.tests;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.resourcemanager.config.SpringSecurityConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringSecurityConfig.class)
public class ProjectControllerTest {

	@Autowired
	private WebApplicationContext	context;

	private MockMvc					mvc;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenRequestMapping_whenDeleteProject0_thenRedirectToEditJSP()
		throws Exception {

		mvc.perform(get("/projects/delete/2")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/projects"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenRequestMapping_whenEditProject0_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/projects/edit/0")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/projects/edit.jsp"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenRequestMapping_whenEditResource0_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/resources/edit/0")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/resources/edit.jsp"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenRequestMapping_whenGetProjects_thenForwardToProjectsJSP()
		throws Exception {

		mvc.perform(get("/projects")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/projects.jsp"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenValidRequest_whenAddProject_thenProjectSaved() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		params.add("id", "0");
		params.add("name", "Test Project");

		// allocations
		params.add("allocation_0_startDate", "21/01/2019");
		params.add("allocation_0_endDate", "28/01/2019");
		params.add("allocation_0_hours", "20");
		params.add("allocation_0_skillId", "1");

		params.add("allocation_1_startDate", "21/02/2019");
		params.add("allocation_1_endDate", "28/02/2019");
		params.add("allocation_1_hours", "40");
		params.add("allocation_1_skillId", "2");

		mvc.perform(post("/projects/save")
			.params(params))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/projects/edit/7"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenValidRequest_whenSaveProject_thenProjectSaved() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		params.add("id", "1");
		params.add("name", "Test Project");

		mvc.perform(post("/projects/save")
			.params(params))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/projects/edit/1"));
	}

	@Before
	public void setup() {
		mvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(springSecurity())
			.build();
	}
	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenRequestMapping_whenGetViewProjects_thenForwardToViewProjectsJSP()
		throws Exception {

		mvc.perform(get("/ViewProjects")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/ViewProjects.jsp"));
	}
}