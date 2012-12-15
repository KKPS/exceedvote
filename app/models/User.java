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
	
	@OneToOne
	public Role role;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);
	
	public String toString() {
		return "";
	}
	
}
