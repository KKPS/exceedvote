package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Settings extends Controller {

	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		return ok(views.html.setting.render(user, Role.find.all(), Project.find.all()));
	}
	
	public static Result update() {
		User user_before = User.find.where().eq("username", request().username()).findUnique();
		DynamicForm requestForm = form().bindFromRequest();
		String username = requestForm.get("username");
		String password = requestForm.get("password");
		String name = requestForm.get("name");
		Long project_id = Long.parseLong(requestForm.get("project_id"));
		Long role_id = Long.parseLong(requestForm.get("role_id"));
		Project project;
		if (project_id == 999) {
			project = null;
		}
		else {
			project = Project.find.ref(project_id);
		}
		Role role = Role.find.ref(role_id);
		boolean isAdmin = user_before.isAdmin();
		boolean firstLogin = user_before.isFirstLogin();
		User new_user = new User(username, password, name, role, project, isAdmin, firstLogin);
		new_user.update(user_before.id);
		if (session("username") == user_before.username) {
			session("username", new_user.username);
		}
		return ok(views.html.setting.render(new_user, Role.find.all(), Project.find.all()));
	}
	
}
