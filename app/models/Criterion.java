package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Criterion extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	public String question;

	public static Finder<Long, Criterion> find = new Finder<Long, Criterion>(Long.class, Criterion.class);
	
	public Criterion() {
		// TODO Auto-generated constructor stub
	}
	
	public Criterion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String toString() {
		return "";
	}
	
	// Additional logic
	/**
	 * Find User's Ballot remainder for this criterion
	 * @param user
	 * @return 
	 */
	public int ballotRemainder(User user) {
		return Ballot.find.where().eq("criterion", this).eq("user", user).findList().size();
	}
	
}
