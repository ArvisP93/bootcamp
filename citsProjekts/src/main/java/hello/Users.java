package hello;

public class Users {
	public int user_id;
	public String username;
	public String password;
	public String role;
	
	public Users() {} //constructor for testing purposes

	public Users(int user_id, String username, String password, String role) {
		setUser_id(user_id);
		setUsername(username);
		setPassword(password);
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
}
