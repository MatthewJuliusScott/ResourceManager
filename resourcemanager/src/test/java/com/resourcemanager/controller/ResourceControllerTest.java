package com.resourcemanager.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResourceControllerTest {

	@Autowired
	private WebApplicationContext	context;

	private MockMvc					mvc;

	@MockBean
	private ResourceService			resourceService;

	@MockBean
	private SkillService			skillService;

	@Test
	@WithMockUser(username = "user", password = "password", roles = "ADMIN")
	public void givenRequestMapping_whenEditResource0_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/resources/edit/0")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/resources/edit.jsp"));
	}

	@Test
	@WithMockUser(username = "user", password = "password", roles = "ADMIN")
	public void givenRequestMapping_whenGetResources_thenForwardToResourcesJSP()
		throws Exception {

		mvc.perform(get("/resources")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/resources.jsp"));
	}

	@Before
	public void setup() {
		mvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(springSecurity())
			.build();
	}
}