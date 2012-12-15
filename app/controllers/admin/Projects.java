package controllers.admin;

import controllers.Secured;
import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Projects extends Controller {

	static Form<Project> projectForm = form(Project.class);
	
	public static Result index() {
		return ok(views.html.projects_admin.render(Project.find.all(), projectForm));
	}
	
	public static Result project(String name) {
		Project project = Project.find.where().eq("name", name).findUnique();
		Form<Project> editForm = form(Project.class);
		if (project != null) {
			editForm = editForm.fill(project);
		}
		return ok(views.html.project_admin.render(project, editForm));
	}
	
	public static Result create() {
		Form<Project> createForm = form(Project.class).bindFromRequest();
		createForm.get().save();
		return ok(views.html.projects_admin.render(Project.find.all(), projectForm));
	}
	
	public static Result update(String name) {
		Project project = Project.find.where().eq("name", name).findUnique();
		Form<Project> updateForm = form(Project.class).bindFromRequest();
		Project updated = updateForm.get();
		updated.update(project.id);
		return ok(views.html.project_admin.render(updated, updateForm));
	}
	
	public static Result delete(String name) {
		Project project = Project.find.where().eq("name", name).findUnique();
		project.delete();
		return ok(views.html.projects_admin.render(Project.find.all(), projectForm));
	}
	
}
