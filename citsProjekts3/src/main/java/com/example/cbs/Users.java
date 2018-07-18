package com.example.cbs;

import java.util.regex.Pattern;

public class Users {
	private int user_id;
	private String username;
	private String password;
	private String email;
	private String role;
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	
	public Users() {} //constructor for testing purposes

	public Users(int user_id, String username, String password, String email, String role) {
		setUser_id(user_id);
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setRole(role);
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		if(user_id <= 0) {
			throw new IllegalArgumentException();
		}else {
			this.user_id = user_id;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username == "") {
			throw new IllegalArgumentException();
		}else {
			this.username = username;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password == "") {
			throw new IllegalArgumentException();
		}else {
			this.password = password;
		}
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		if(role == "") {
			throw new IllegalArgumentException();
		}else {
			this.role = role;
		}
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (VALID_EMAIL_ADDRESS_REGEX.matcher(email).find())
			this.email = email;
		else
			throw new IllegalArgumentException();
	}
}