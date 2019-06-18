package com.resourcemanager.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.resourcemanager.model.Resource;
import com.resourcemanager.service.ResourceService;
import com.resourcemanager.service.SkillService;

@RunWith(SpringRunner.class)
@WebMvcTest(ResourceController.class)
public class ResourceControllerTest {

	@Autowired
	private MockMvc			mvc;

	@MockBean
	private ResourceService	resourceService;

	@MockBean
	private SkillService	skillService;

	@Test
	public void givenResources_whenGetResources_thenReturnJsonArray()
		throws Exception {

		Resource expected = new Resource();
		expected.setName("Test Person");

		List<Resource> allResources = Arrays.asList(expected);

		given(resourceService.listResources()).willReturn(allResources);

		mvc.perform(get("/resources")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].name", is(expected.getName())));
	}
}