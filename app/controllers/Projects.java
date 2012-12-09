package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Projects extends Controller {

	static Form<Project> projectForm = form(Project.class);
	
	public static Result index() {
		return ok(views.html.projects.render(Project.find.all(), projectForm));
	}
	
	public static Result project(String name) {
		Project project = Project.find.where().eq("name", name).findUnique();
		Form<Project> editForm = form(Project.class);
		if (project != null) {
			editForm = editForm.fill(project);
		}
		return ok(views.html.project.render(project, editForm));
	}
	
	public static Result create() {
		Form<Project> insertForm = form(Project.class).bindFromRequest();
		insertForm.get().save();
		return ok(views.html.projects.render(Project.find.all(), projectForm));
	}
	
	public static Result update(String name) {
		Project project = Project.find.where().eq("name", name).findUnique();
		Form<Project> updateForm = form(Project.class).bindFromRequest();
		Project updated = updateForm.get();
		updated.update(project.id);
		return ok(views.html.project.render(updated, updateForm));
	}
	
	public static Result delete(String name) {
		Project project = Project.find.where().eq("name", name).findUnique();
		project.delete();
		return ok(views.html.projects.render(Project.find.all(), projectForm));
	}
	
}
