package com.resourcemanager.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SkillControllerTest {

	@Autowired
	private WebApplicationContext	context;

	private MockMvc					mvc;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = "ADMIN")
	public void givenRequestMapping_whenDeleteSkill0_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/skills/delete/1")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/skills"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = "ADMIN")
	public void givenRequestMapping_whenEditResource0_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/resources/edit/0")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/resources/edit.jsp"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = "ADMIN")
	public void givenRequestMapping_whenEditSkill0_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/skills/edit/0")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/skills/edit.jsp"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = "ADMIN")
	public void givenRequestMapping_whenGetSkills_thenForwardToSkillsJSP()
		throws Exception {

		mvc.perform(get("/skills")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/skills.jsp"));
	}

	@Before
	public void setup() {
		mvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(springSecurity())
			.build();
	}
}