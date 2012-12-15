package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Role extends Model {

	@Id
	public Long id;
	
	public String name;

	public int ballot;
	
	public static Finder<Long, Role> find = new Finder<Long, Role>(Long.class, Role.class);
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	public Role(String name, int ballot) {
		this.name = name;
		this.ballot = ballot;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBallot() {
		return ballot;
	}

	public void setBallot(int ballot) {
		this.ballot = ballot;
	}
	
}
