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
		return TODO;
	}

}
