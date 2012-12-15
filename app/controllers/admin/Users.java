package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Users extends Controller {

	public static Result index() {
		return ok(views.html.users_admin.render(User.find.all(), form(User.class)));
	}
	
}
