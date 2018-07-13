package hello;

public class Users {
	public int user_id;
	public String username;
	public String password;
	public String role;
	
	public Users(int uid, String user, String pass, String role) {
		this.user_id=uid;
		this.username=user;
		this.password=pass;
		this.role=role;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
