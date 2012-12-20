package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import controllers.*;
import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Projects extends Controller {

	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		if (user.isAdmin) {
			return ok(views.html.projects_admin.render(user, Project.find.all()));
		}
		else {
			return redirect("/");
		}
	}

	public static Result project(String name) {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Project project = Project.find.where().eq("name", name).findUnique();
		Form<Project> editForm = form(Project.class);
		if (user.isAdmin) {
		return ok(views.html.project_admin.render(user, project));
		}
		else {
			return redirect("/");
		}
	}

	public static Result create() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Form<Project> createForm = form(Project.class).bindFromRequest();
		createForm.get().save();
		return ok(views.html.projects_admin.render(user, Project.find.all()));
	}

	public static Result update(String name) {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Project project = Project.find.where().eq("name", name).findUnique();
		Form<Project> updateForm = form(Project.class).bindFromRequest();
		Project updated = updateForm.get();
		updated.update(project.id);
		return ok(views.html.project_admin.render(user, updated));
	}

	public static Result delete(String name) {
		User user = User.find.where().eq("username", request().username()).findUnique();
		if (!user.isAdmin) {
			return redirect("/");
		}
		Project project = Project.find.where().eq("name", name).findUnique();
		project.delete();
		return redirect("/admin/project");
	}

}
