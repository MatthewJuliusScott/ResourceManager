package com.resourcemanager.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.impl.UserDAOImpl;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserDetailServiceImplTest {

	@Autowired
	UserDAOImpl userDAO;

	@Test
	public void givenRepository_whenGetUsers_thenUsersReturned() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void givenRepository_whenLoadUserByUsername_thenUserReturned() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends UserDetailServiceImpl> clazz = new UserDetailServiceImpl().getClass();
		assertTrue(clazz.equals(UserDetailServiceImpl.class));
	}
}
