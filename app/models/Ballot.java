package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Ballot extends Model {

	@Id
	public Long id;
	
	@OneToOne
	public User user;
	
	@OneToOne
	public Project project;
	
	@OneToOne
	public Criterion criterion;
	
	public Ballot(User user, Project project, Criterion criterion) {
		this.user = user;
		this.project = project;
		this.criterion = criterion;
	}
	
	public static Finder<Long, Ballot> find = new Finder<Long, Ballot>(Long.class, Ballot.class);
	
	public String toString() {
		return "";
	}
	
}
