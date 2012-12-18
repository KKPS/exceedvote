package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return TODO;
	}
	
	// Authenticate
	public static class Login {
		
		public String username;
		public String password;
		
		public String validate() {
			if (User.find.where().eq("username", username).eq("password", password).findUnique() == null) {
				return "Invalid Username or Password";
			}
			return null;
		}
		
	}
	
	public static Result login() {
		return ok(views.html.login.render(form(Login.class)));
	}
	
	public static Result authenticate() {
		Form<Login> loginForm = form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(views.html.login.render(loginForm));
		}
		else {
			session("username", loginForm.get().username);
			User user = User.find.where().eq("username", session("username")).findUnique();
			if (user.firstLogin == true) {
				return redirect(routes.FirstLogin.index());
			}
			else {
				return redirect(routes.Votes.index());
			}
		}
	}
	
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return ok(views.html.login.render(form(Login.class)));
	}
	
}