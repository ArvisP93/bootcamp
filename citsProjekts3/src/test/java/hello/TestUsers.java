package hello;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.example.cbs.Users;

public class TestUsers {

	@Test
	public void testConstructor(){
		try {
			Users obj = new Users(0, "NewUser", "NewPassword", "newemail@gmail.com", "user");
			fail("Exception was expected for 0 id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(-10, "NewUser", "NewPassword", "newemail@gmail.com", "user");
			fail("Exception was expected for negative id input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "", "NewPassword", "newemail@gmail.com", "user");
			fail("Exception was expected for empty username input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUsernamethatisverylongusernameforthesettermethod", "NewPassword", "newemail@gmail.com", "user");
			fail("Exception was expected for invalid username input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUser", "", "newemail@gmail.com", "user");
			fail("Exception was expected for empty password input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUser", "newpasswordthatistoolongforthepasswordsetter", "newemail@gmail.com", "user");
			fail("Exception was expected for invalid password input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUser", "NewPassword", "", "user");
			fail("Exception was expected for empty email input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUser", "NewPassword", "newemailgmailcom", "user");
			fail("Exception was expected for invalid email input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUser", "NewPassword", "newemailthatisveryveryverylongandshouldcouseerrorsbecauseitislongerthensetterallowesittobe@gmail.com", "user");
			fail("Exception was expected for invalid email input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUser", "NewPassword", "newemail@gmail.com", "");
			fail("Exception was expected for invalid email input");
		} catch(IllegalArgumentException e) {
		}
		try {
			Users obj = new Users(101, "NewUser", "NewPassword", "newemail@gmail.com", "moderator");
			fail("Exception was expected for invalid user input");
		} catch(IllegalArgumentException e) {
		}	
	}

	@Test
	public void testId() {
		Users obj = new Users();
		try {
			obj.setUser_id(0);
			fail("Exception was expected for 0 id input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setUser_id(-100);
			fail("Exception was expected for negative id input");
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testPassword() {
		Users obj = new Users();
		try {
			obj.setUsername("");
			fail("Exception was expected for empty password input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setUsername("NewUsernamethatisverylongusernameforthesettermethod");
			fail("Exception was expected for invalid password input");
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testEmail() {
		Users obj = new Users();
		try {
			obj.setEmail("");
			fail("Exception was expected for empty email input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setEmail("newemailgmailcom");
			fail("Exception was expected for invalid email input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setEmail("newemailthatisveryveryverylongandshouldcouseerrorsbecauseitislongerthensetterallowesittobe@gmail.com");
			fail("Exception was expected for invalid email input");
		}catch(IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testRole() {
		Users obj = new Users();
		try {
			obj.setRole("");
			fail("Exception was expected for empty role input");
		}catch(IllegalArgumentException e) {
		}
		try {
			obj.setRole("moderator");
			fail("Exception was expected for invalid role input");
		}catch(IllegalArgumentException e) {
		}
	}
	
	
	
	
}
