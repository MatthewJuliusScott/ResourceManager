package com.resourcemanager.model;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class UserDetailsTest {

	@Test
	public void testClone() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = (UserDetails) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends UserDetails> clazz = new UserDetails().getClass();
		assertTrue(clazz.equals(UserDetails.class));
	}

	@Test
	public void testGetId() throws Exception {
		UserDetails userdetails = new UserDetails();
		Long id = new Random().nextLong();
		userdetails.setId(id);
		assertTrue(Long.valueOf(userdetails.getId()).equals(id));
	}

	@Test
	public void testHashCode() throws Exception {
		UserDetails x = new UserDetails();
		UserDetails y = new UserDetails();
		x.setId(1L);
		x.setEmail("test@email.com");
		x.setName("Test Name");
		x.setPassword("password");
		y.setId(1L);
		y.setEmail("test@email.com");
		y.setName("Test Name");
		y.setPassword("password");
		assertTrue(x.equals(y) && y.equals(x));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testNotEqualsEmail() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("not@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsEmailNull() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail(null);
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsId() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(2L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsName() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Not Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsNameNull() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName(null);
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsOtherEmailNull() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail(null);
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsOtherNameNull() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName(null);
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsOtherPasswordNull() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword(null);

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsPassword() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("notpassword");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsPasswordNull() throws Exception {
		UserDetails expected = new UserDetails();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		UserDetails actual = new UserDetails();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword(null);

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testSetId() throws Exception {
		UserDetails userdetails = new UserDetails();
		Long id = new Random().nextLong();
		userdetails.setId(id);
		assertTrue(Long.valueOf(userdetails.getId()).equals(id));
	}

	@Test
	public void testToString() throws Exception {
		UserDetails userDetails = new UserDetails();
		userDetails.setId(1L);
		userDetails.setEmail("test@email.com");
		userDetails.setName("Test Name");
		userDetails.setPassword("password");
		assertTrue(userDetails.toString()
			.equals("UserDetails [id=1, name=Test Name, email=test@email.com, password=password]"));
	}

	@Test
	public void testUserDetails() throws Exception {
		UserDetails userdetails = new UserDetails();
		assertTrue(userdetails != null);
	}
}
