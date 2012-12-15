package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class User extends Model {

	@Id
	@Constraints.Required
	public String username;
	
	@Constraints.Required
	public String password;
	
	public String name;
	
	@OneToOne
	public Role role;
	
	public boolean isAdmin;
	
	public boolean firstLogin;
	
	public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.isAdmin = false;
		this.firstLogin = true;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String toString() {
		return "";
	}
	
}
