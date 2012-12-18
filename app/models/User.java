package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class User extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	public String username;
	
	@Constraints.Required
	public String password;
	
	public String name;
	
	@OneToOne
	public Role role;
	
	@OneToOne
	public Project project;
	
	public boolean isAdmin;
	
	public boolean firstLogin;
	
	public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
	
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

	public User(String username, String password, String name, Role role, Project project, boolean isAdmin, boolean firstLogin) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.project = project;
		this.isAdmin = isAdmin;
		this.firstLogin = firstLogin;
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

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String toString() {
		return "";
	}
	
	// Additional logic
	/**
	 * Find all Ballot send by this User
	 * @return
	 */
	public List<Ballot> ballotThisUser() {
		return Ballot.find.where().eq("user", this).findList();
	}
	
}
