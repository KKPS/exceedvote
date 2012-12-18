package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Requests extends Controller {
	
	public static Result index() {
		// return ok(views.html.request.render());
		return TODO;
	}
	
	public static Result create() {
		Request request = null;
		// return ok(views.html.request_result.render(request));
		return TODO;
	}
	
	public static Result item(String username) {
		// Request request = Request.find.where().eq("username", username).findUnique();
		Request request = null;
		return ok(views.html.request_result.render(request));
	}

}
