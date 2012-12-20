package controllers.admin;

import play.*;
import play.data.*;
import play.mvc.*;

import controllers.*;
import models.*;
import views.html.*;

@Security.Authenticated(Secured.class)
public class Criteria extends Controller {

	public static Result index() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		if (user.isAdmin) {
			return ok(views.html.criteria_admin.render(user, Criterion.find.all()));
		}
		else {
			return redirect("/");
		}
	}

	public static Result criterion(Long id) {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Criterion criterion = Criterion.find.where().eq("id", id).findUnique();
		Form<Criterion> editForm = form(Criterion.class);
		if (user.isAdmin) {
			return ok(views.html.criterion_admin.render(user, criterion, criterion.ballotThisCriterion()));
		}
		else {
			return redirect("/");
		}
	}

	public static Result create() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Form<Criterion> createForm = form(Criterion.class).bindFromRequest();
		createForm.get().save();
		if (user.isAdmin) {
			return ok(views.html.criteria_admin.render(user, Criterion.find.all()));
		}
		else {
			return redirect("/");
		}
	}

	public static Result update(Long id) {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Criterion criterion = Criterion.find.ref(id);
		Form<Criterion> updateForm = form(Criterion.class).bindFromRequest();
		Criterion updated = updateForm.get();
		updated.update(criterion.id);
		if (user.isAdmin) {
			return ok(views.html.criterion_admin.render(user, updated, updated.ballotThisCriterion()));
		}
		else {
			return redirect("/");
		}
	}

	public static Result delete(Long id) {
		User user = User.find.where().eq("username", request().username()).findUnique();
		if (!user.isAdmin) {
			return redirect("/");
		}
		Criterion criterion = Criterion.find.ref(id);
		criterion.delete();
		return redirect("/admin/criterion");
	}

}
