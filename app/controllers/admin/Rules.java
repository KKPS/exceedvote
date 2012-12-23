package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import controllers.*;
import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Rules extends Controller {
	
	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Long rule_id = (long) 1;
		return ok(views.html.rule_admin.render(user, Rule.find.ref(rule_id)));
	}

}
