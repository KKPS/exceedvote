package controllers;

import play.*;
import play.data.*;
import play.mvc.*;

import models.*;
import views.html.*;

public class Criteria extends Controller {

	public static Result index() {
		return ok(views.html.criteria.render(Criterion.find.all()));
	}
	
	public static Result create() {
		Form<Criterion> createForm = form(Criterion.class).bindFromRequest();
		createForm.get().save();
		return ok(views.html.criteria.render(Criterion.find.all()));
	}
	
}
