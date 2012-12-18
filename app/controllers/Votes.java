package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Votes extends Controller {
	
	static Form<Ballot> ballotForm = form(Ballot.class);

	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		return ok(views.html.vote.render(user, Criterion.find.all(), Project.find.all(), ballotForm));
	}
	
	public static Result vote() {
		User login = User.find.where().eq("username", request().username()).findUnique();
		DynamicForm requestForm = form().bindFromRequest();
		Long user_id = Long.parseLong(requestForm.get("user_id"));
		Long project_id = Long.parseLong(requestForm.get("project_id"));
		Long criterion_id = Long.parseLong(requestForm.get("criterion_id"));
		User user = User.find.ref(user_id);
		Project project = Project.find.ref(project_id);
		Criterion criterion = Criterion.find.ref(criterion_id);
		new Ballot(user, project, criterion).save();
		return ok(views.html.vote.render(login, Criterion.find.all(), Project.find.all(), ballotForm));
	}
	
}
