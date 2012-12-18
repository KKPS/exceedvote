package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import controllers.*;
import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Roles extends Controller {
	
	public static Result index() {
		return ok(views.html.roles_admin.render(Role.find.all()));
	}
	
	public static Result role(Long id) {
		return ok(views.html.role_admin.render(Role.find.ref(id)));
	}

}
