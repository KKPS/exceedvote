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
	
	public String imgUrl1;
	
	public String imgUrl2;
	
	public String imgUrl3;
	
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

	public String getImgUrl1() {
		return imgUrl1;
	}

	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}

	public String getImgUrl2() {
		return imgUrl2;
	}

	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}

	public String getImgUrl3() {
		return imgUrl3;
	}

	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}

	public String toString() {
		return "";
	}
	
	// Additional logic
	public int score(Criterion criterion) {
		return Ballot.find.where().eq("project", this).eq("criterion", criterion).findList().size();
	}
	
	public int thisScore() {
		return Ballot.find.where().eq("project", this).findList().size();
	}
	
}
