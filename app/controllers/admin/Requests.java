package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import controllers.*;
import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Requests extends Controller {

	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		if (user.isAdmin) {
			return ok(views.html.requests_admin.render(user, Request.find.all()));
		}
		else {
			return redirect("/");
		}
	}
	
	public static Result create() {
		User user_login = User.find.where().eq("username", request().username()).findUnique();
		DynamicForm requestForm = form().bindFromRequest();
		String username = requestForm.get("username");
		String password = requestForm.get("password");
		Long role_id = Long.parseLong(requestForm.get("role_id"));
		Role role = Role.find.ref(role_id);
		new User(username, password, role).save();
		Long request_id = Request.find.where().eq("username", username).findUnique().getId();
		Request new_request = Request.find.where().eq("username", username).findUnique();
		new_request.setApprove(true);
		new_request.update(request_id);
		return ok(views.html.requests_admin.render(user_login, Request.find.all()));
	}
	
}
