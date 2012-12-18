package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Criterion extends Model {

	@Id
	public Long id;
	
	@Constraints.Required
	public String question;
	
	public String description;

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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "";
	}
	
	// Additional logic
	/**
	 * Find all Ballot voted in this Criterion
	 * @return
	 */
	public List<Ballot> ballotThisCriterion() {
		return Ballot.find.where().eq("criterion", this).findList();
	}
	
	/**
	 * Find User's Ballot remainder for this criterion
	 * @param user
	 * @return 
	 */
	public int ballotUsage(User user) {
		if (Ballot.find.where().eq("criterion", this).eq("user", user).findList() == null) {
			return 0;
		}
		else {
			return Ballot.find.where().eq("criterion", this).eq("user", user).findList().size();
		}
	}
	
}
