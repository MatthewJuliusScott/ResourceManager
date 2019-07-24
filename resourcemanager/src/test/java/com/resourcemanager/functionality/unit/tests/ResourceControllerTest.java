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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResourceControllerTest {

	@Autowired
	private WebApplicationContext	context;

	private MockMvc					mvc;

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenRequestMapping_whenAddProject_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/resources/add")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/resources/edit/0"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenRequestMapping_whenDeleteProject0_thenForwardToEditJSP()
		throws Exception {

		mvc.perform(get("/resources/delete/1")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/resources"));
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
	public void givenRequestMapping_whenGetResources_thenForwardToResourcesJSP()
		throws Exception {

		mvc.perform(get("/resources")
			.contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/resources.jsp"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenValidRequest_whenAddResource_thenResourceSaved() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		params.add("id", "0");
		params.add("name", "Test Resource");
		params.add("hours", "40");
		params.add("skills", "1");
		params.add("skills", "2");
		params.add("skills", "3");

		mvc.perform(post("/resources/save")
			.params(params))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/resources"));
	}

	@Test
	@WithMockUser(username = "user@gmail.com", password = "password", roles = { "USER", "ADMIN" })
	public void givenValidRequest_whenSaveResource_thenResourceSaved() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		params.add("id", "1");
		params.add("name", "Test Resource");
		params.add("hours", "40");
		params.add("skills", "1");
		params.add("skills", "2");
		params.add("skills", "3");

		mvc.perform(post("/resources/save")
			.params(params))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/resources"));
	}

	@Before
	public void setup() {
		mvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(springSecurity())
			.build();
	}
}