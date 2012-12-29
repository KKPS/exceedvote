package controllers.admin;

import java.util.*;

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
	
	public static Result update() {
		User user = User.find.where().eq("username", request().username()).findUnique();
		Rule rule = Rule.find.ref((long) 1);
		DynamicForm requestForm = form().bindFromRequest();
		String[] date = requestForm.get("date").split("/");
		// date[0] = Month, date[1] = Day, date[2] = Year
		int month = Integer.parseInt(date[0]) - 1;
		int day = Integer.parseInt(date[1]);
		int year = Integer.parseInt(date[2]) - 1900;
		System.out.println();
		String start = null;
		String start_in = requestForm.get("start");
		if (start_in.length() == 6) {
			StringBuilder start_build = new StringBuilder("0");
			start_build.append(start_in);
			start = start_build.toString();
		}
		else {
			start = start_in;
		}
		String stype = start.substring(5, 7);
		int shh = Integer.parseInt(start.substring(0, 2));
		int smm = Integer.parseInt(start.substring(3, 5));
		if (stype.equals("pm")) {
			if (shh < 12) {
				shh += 12;
			}
		}
		// java.util.Date constuctor Date(int year, int month, int date, int hrs, int min)
		Date new_start = new Date(year, month, day, shh, smm);
		String finish = null;
		String finish_in = requestForm.get("finish");
		if (finish_in.length() == 6) {
			StringBuilder finish_build = new StringBuilder("0");
			finish_build.append(finish_in);
			finish = finish_build.toString();
		}
		else {
			finish = finish_in;
		}
		String ftype = finish.substring(5, 7);
		int fhh = Integer.parseInt(finish.substring(0, 2));
		int fmm = Integer.parseInt(finish.substring(3, 5));
		if (ftype.equals("pm")) {
			if (fhh < 12) {
				fhh += 12;
			}
		}
		Date new_finish = new Date(year, month, day, fhh, fmm);
		Rule new_rule = new Rule(new_start, new_finish);
		new_rule.update(rule.id);
		return ok(views.html.rule_admin.render(user, new_rule));
	}

}
