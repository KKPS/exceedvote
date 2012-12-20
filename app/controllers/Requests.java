package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Requests extends Controller {
	
	public static Result index() {
		return ok(views.html.request.render(Role.find.all()));
	}
	
	public static Result create() {
		DynamicForm requestForm = form().bindFromRequest();
		String username = requestForm.get("username");
		String password = requestForm.get("password");
		Long role_id = Long.parseLong(requestForm.get("role_id"));
		Role role = Role.find.ref(role_id);
		Request request = new Request(username, password, role);
		request.save();
		// return ok(views.html.request_result.render(request));
		return redirect("/request/" + request.getUsername());
	}
	
	public static Result item(String username) {
		Request request = Request.find.where().eq("username", username).findUnique();
		return ok(views.html.request_result.render(request));
	}

}
