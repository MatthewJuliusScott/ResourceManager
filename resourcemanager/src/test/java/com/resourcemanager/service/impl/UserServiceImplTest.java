package com.resourcemanager.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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
	public void givenRepository_whenStressTestLoadUsersById_thenUsersReturned() throws Exception {

		List<Callable<Object>> callableTasks = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(10);

		for (int i = 1; i < 29; i++) {

			final int id = i;

			callableTasks.add(new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					User userdetails = userDAO.findUserByID(id);
					assertNotNull(userdetails);
					return null;
				}

			});

		}

		List<Future<Object>> futures = executor.invokeAll(callableTasks);
		for (Future<Object> future : futures) {
			future.get(10, TimeUnit.SECONDS);
		}
		executor.shutdown();
		try {
			if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			throw e;
		}
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends UserServiceImpl> clazz = new UserServiceImpl().getClass();
		assertTrue(clazz.equals(UserServiceImpl.class));
	}

}
