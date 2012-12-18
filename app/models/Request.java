package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Request extends Model {
	
	@Id
	public Long id;
	
	@Constraints.Required
	public String username;
	
	@Constraints.Required
	public String password;
	
	public boolean approve;
	
	public static Finder<Long, Request> find = new Finder<Long, Request>(Long.class, Request.class);
	
	public Request() {
		// TODO Auto-generated constructor stub
	}

	public Request(String username, String password) {
		this.username = username;
		this.password = password;
		this.approve = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

}
