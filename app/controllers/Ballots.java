package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Ballots extends Controller {
	
	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		return ok(views.html.ballots.render(user, user.ballotThisUser()));
	}
	
}
