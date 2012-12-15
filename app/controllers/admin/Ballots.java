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
		return ok(views.html.ballots_admin.render(Ballot.find.all()));
	}
	
}
