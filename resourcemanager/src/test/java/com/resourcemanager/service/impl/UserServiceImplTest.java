package com.resourcemanager.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.resourcemanager.dao.impl.UserDAOImpl;
import com.resourcemanager.model.User;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceImplTest {

	@Autowired
	UserDAOImpl userDAO;

	@Test
	public void givenRepository_whenGetUsers_thenUsersReturned() throws Exception {
		List<User> skills = userDAO.listUsers();
		assertNotNull(skills);
		assertTrue(skills.size() > 0);
	}

	@Test
	public void givenRepository_whenLoadUserByUsername_thenUserReturned() throws Exception {
		User userdetails = userDAO.findUserByEmail("user@gmail.com");
		assertNotNull(userdetails);
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends UserServiceImpl> clazz = new UserServiceImpl().getClass();
		assertTrue(clazz.equals(UserServiceImpl.class));
	}

}
