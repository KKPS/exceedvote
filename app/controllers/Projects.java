package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Projects extends Controller {

	static Form<Project> projectForm = form(Project.class);
	
	public static Result index() {
		return ok(projects.render(Project.find.all(), projectForm));
	}
	
	public static Result project(String name) {
		return ok(project.render(Project.find.where().eq("name", name).findUnique()));
	}
	
	public static Result create() {
		Form<Project> insertForm = form(Project.class).bindFromRequest();
		insertForm.get().save();
		return ok(projects.render(Project.find.all(), projectForm));
	}
	
	public static Result delete(Long id) {
		Project.find.ref(id).delete();
		return ok(projects.render(Project.find.all(), projectForm));
	}
	
}
