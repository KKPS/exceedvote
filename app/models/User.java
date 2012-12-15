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
	
	public static Finder<String, User> find = new Finder<String, User>(String.class, User.class);
	
	public String toString() {
		return "";
	}
	
}
