package com.resourcemanager.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserTest {

	@Test
	public void testClone() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = (User) expected.clone();
		assertTrue(expected.equals(actual));
	}

	@Test
	public void testEquals() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testGetAuthorities() throws Exception {
		User userDetails = new User();
		Long id = new Random().nextLong();
		userDetails.setId(id);
		userDetails.getAuthorityStrings().add("ROLE_USER");

		List<GrantedAuthority> expected = new ArrayList<GrantedAuthority>();
		expected.add(new SimpleGrantedAuthority("ROLE_USER"));

		List<GrantedAuthority> actual = userDetails.getAuthorities();

		assertEquals(expected, actual);

	}

	@Test
	public void testGetClass() throws Exception {
		Class<? extends User> clazz = new User().getClass();
		assertTrue(clazz.equals(User.class));
	}

	@Test
	public void testGetId() throws Exception {
		User userdetails = new User();
		Long id = new Random().nextLong();
		userdetails.setId(id);
		assertTrue(Long.valueOf(userdetails.getId()).equals(id));
	}

	@Test
	public void testHashCode() throws Exception {
		User x = new User();
		User y = new User();
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
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("not@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsEmailNull() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail(null);
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsId() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(2L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsName() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Not Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsNameNull() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName(null);
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsOtherEmailNull() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail(null);
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsOtherNameNull() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName(null);
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsOtherPasswordNull() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword(null);

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("password");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsPassword() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword("notpassword");

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testNotEqualsPasswordNull() throws Exception {
		User expected = new User();
		expected.setId(1L);
		expected.setEmail("test@email.com");
		expected.setName("Test Name");
		expected.setPassword("password");

		User actual = new User();
		actual.setId(1L);
		actual.setEmail("test@email.com");
		actual.setName("Test Name");
		actual.setPassword(null);

		assertTrue(!expected.equals(actual));
	}

	@Test
	public void testSetId() throws Exception {
		User userdetails = new User();
		Long id = new Random().nextLong();
		userdetails.setId(id);
		assertTrue(Long.valueOf(userdetails.getId()).equals(id));
	}

	@Test
	public void testToString() throws Exception {
		User userDetails = new User();
		userDetails.setId(1L);
		userDetails.setEmail("test@email.com");
		userDetails.setName("Test Name");
		userDetails.setPassword("password");
		assertTrue(userDetails.toString()
			.equals("UserDetails [id=1, name=Test Name, email=test@email.com, password=password]"));
	}

	@Test
	public void testUserDetails() throws Exception {
		User userdetails = new User();
		assertTrue(userdetails != null);
	}
}
