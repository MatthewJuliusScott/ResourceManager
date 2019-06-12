package com.resourcemanager.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.resourcemanager.model.Resource;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ResourceDAOImplTest {

	@Autowired
	private TestEntityManager	entityManager;

	@Autowired
	private ResourceDAOImpl		resourceRepository;

	@Test
	public void whenFindById_thenReturnResource() {
		// given
		Resource resource = new Resource();
		resource.setId(100);

		entityManager.persist(resource);
		entityManager.flush();

		// when
		Resource found = resourceRepository.getResourceById(resource.getId());

		// then
		assertThat(Long.valueOf(found.getId()))
			.isEqualTo(Long.valueOf(resource.getId()));
	}

}
