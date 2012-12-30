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
		User user_login = User.find.where().eq("username", request().username()).findUnique();
		if (user_login.isAdmin()) {
			return ok(views.html.users_admin.render(user_login, User.find.all(), Role.find.all(), Project.find.all()));
		}
		else {
			return redirect("/");
		}
	}
	
	public static Result user(String username) {
		User user_login = User.find.where().eq("username", request().username()).findUnique();
		User user = User.find.where().eq("username", username).findUnique();
		// Form<User> editForm = form(User.class);
		if (user_login.isAdmin) {
			return ok(views.html.user_admin.render(user_login, user, user.ballotThisUser(), Role.find.all(), Project.find.all()));
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
		Long project_id = Long.parseLong(requestForm.get("project_id"));
		Role role = Role.find.ref(role_id);
		Project project;
		if (project_id == 999) {
			project = null;
		}
		else {
			project = Project.find.ref(project_id);
		}
		new User(username, password, role, project).save();
		return ok(views.html.users_admin.render(user_login, User.find.all(), Role.find.all(), Project.find.all()));
	}
	
	public static Result update(String username) {
		User user_login = User.find.where().eq("username", request().username()).findUnique();
		User edit_user = User.find.where().eq("username", username).findUnique();
		DynamicForm requestForm = form().bindFromRequest();
		String new_username = requestForm.get("new_username");
		String new_password = requestForm.get("new_password");
		String new_name = requestForm.get("new_name");
		Long new_role_id = Long.parseLong(requestForm.get("new_role_id"));
		Long new_project_id = Long.parseLong(requestForm.get("new_project_id"));
		Role new_role = Role.find.ref(new_role_id);
		Project project;
		if (new_project_id == 999) {
			project = null;
		}
		else {
			project = Project.find.ref(new_project_id);
		}
		User update_user = new User(new_username, new_password, new_name, new_role, project, edit_user.isAdmin, edit_user.firstLogin);
		update_user.update(edit_user.id);
		if (session("username").equals(edit_user.username)) {
			session("username", update_user.username);
			return ok(views.html.user_admin.render(update_user, update_user, update_user.ballotThisUser(), Role.find.all(), Project.find.all()));
		}
		else {
			return ok(views.html.user_admin.render(user_login, update_user, update_user.ballotThisUser(), Role.find.all(), Project.find.all()));
		}
	}
	
	public static Result delete(String username) {
		User user_login = User.find.where().eq("username", request().username()).findUnique();
		if (!user_login.isAdmin) {
			return redirect("/");
		}
		User user = User.find.where().eq("username", username).findUnique();
		user.delete();
		return redirect("/admin/user");
	}
	
}
