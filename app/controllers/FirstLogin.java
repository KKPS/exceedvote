package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class FirstLogin extends Controller {
	
	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		return ok(views.html.first_login.render(user, Project.find.all()));
	}
	
	public static Result information() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		return ok(views.html.first_information.render(user));
	}
	
	public static Result update() {
		DynamicForm requestForm = form().bindFromRequest();
		String username = requestForm.get("username");
		System.out.println(username);
		User user_before = User.find.where().eq("username", username).findUnique();
		System.out.println(user_before.username + " " + user_before.password);
		String password = requestForm.get("password");
		String name = requestForm.get("name");
		Long project_id = Long.parseLong(requestForm.get("project_id"));
		Project project = Project.find.ref(project_id);
		Role role = user_before.getRole();
		boolean isAdmin = user_before.isAdmin();
		boolean firstLogin = false;
		User user_after = new User(username, password, name, role, project, isAdmin, firstLogin);
		user_after.update(user_before.id);
		return ok(views.html.first_information.render(user_after));
	}

}
