package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import controllers.*;
import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Ballots extends Controller {
	
	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		if (user.isAdmin) {
			return ok(views.html.ballots_admin.render(user, Ballot.find.all()));
		}
		else {
			return redirect("/");
		}
	}
	
}
