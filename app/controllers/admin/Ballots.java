package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Ballots extends Controller {
	
	public static Result index() {
		return ok(views.html.ballots.render(Ballot.find.all()));
	}
	
}
