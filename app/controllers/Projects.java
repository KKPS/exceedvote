package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Projects extends Controller {

	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Project project = user.project;
		return ok(views.html.projects.render(user, project, Project.find.all()));
	}

	public static Result project(String name) {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Project project = Project.find.where().eq("name", name).findUnique();
		boolean isOwner = false;
		if (user.project != null) {
			if (user.project.equals(project)) {
				isOwner = true;
			}
		}
		return ok(views.html.project.render(user, project, isOwner));
	}

}
