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
	
}
