package com.example.demo;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.modelclasses.Shows;
import com.modelclasses.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUsers {
	@Test
	public void testConstructor() {
		try {
			Users obj = new Users(0, "User1", "qwerty", "none");
			fail("Exception was expected for 0 user_id input");
		} catch (IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(-11, "User1", "qwerty", "none");
			fail("Exception was expected for negative user_id input");
		} catch (IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(11, "", "qwerty", "none");
			fail("Exception was expected for empty username input");
		} catch (IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(11, "User1", "", "none");
			fail("Exception was expected for empty password input");
		} catch (IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(11, "User1", "qwerty", "");
			fail("Exception was expected for empty role input");
		} catch (IllegalArgumentException e) {
		}
		
	}
	
	@Test
	public void testUserId() {
		Users obj = new Users();
		try {
			obj.setUser_id(0);
			fail("Exception was expected for 0 user_id input");
		}
		catch(IllegalArgumentException e) {
		}
		try {
			obj.setUser_id(-11);
			fail("Exception was expected for negative user_id input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testUsername() {
		Users obj = new Users();
		try {
			obj.setUsername("");
			fail("Exception was expected for empty username input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testPassword() {
		Users obj = new Users();
		try {
			obj.setPassword("");
			fail("Exception was expected for empty password input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testRole() {
		Users obj = new Users();
		try {
			obj.setRole("");
			fail("Exception was expected for empty role input");
		}
		catch(IllegalArgumentException e) {
		}
	}
	
}


