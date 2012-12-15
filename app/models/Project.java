package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Project extends Model {
	
	@Id
	public Long id;
	
	@Constraints.Required
	public String name;
	
	public String description;
	
	public static Finder<Long, Project> find = new Finder<Long, Project>(Long.class, Project.class);
	
	public Project() {
		// TODO Auto-generated constructor stub
	}
	
	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "";
	}
	
}
