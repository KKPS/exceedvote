package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import controllers.*;
import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Users extends Controller {

	public static Result index() {
		return ok(views.html.users_admin.render(User.find.all(), Role.find.all(), form(User.class)));
	}
	
	public static Result user(String username) {
		User user = User.find.where().eq("username", username).findUnique();
		Form<User> editForm = form(User.class);
		if (user != null) {
			editForm = editForm.fill(user);
		}
		return ok(views.html.user_admin.render(user, editForm));
	}
	
	public static Result create() {
		DynamicForm requestForm = form().bindFromRequest();
		String username = requestForm.get("username");
		String password = requestForm.get("password");
		Long role_id = Long.parseLong(requestForm.get("role_id"));
		Role role = Role.find.ref(role_id);
		new User(username, password, role).save();
		return ok(views.html.users_admin.render(User.find.all(), Role.find.all(), form(User.class)));
	}
	
	// BUGGGG
	public static Result update(String username) {
		User user = User.find.ref(username);
		Form<User> updateForm = form(User.class).bindFromRequest();
		User updated = updateForm.get();
		updated.update(user.username);
		return ok(views.html.user_admin.render(updated, updateForm));
	}
	
	public static Result delete(String username) {
		User user = User.find.ref(username);
		user.delete();
		return ok(views.html.users_admin.render(User.find.all(), Role.find.all(), form(User.class)));
	}
	
}
