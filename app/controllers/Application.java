package controllers;

import models.Category;
import models.Posting;
import models.User;
import play.mvc.Controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

public class Application extends Controller {

	private static final SecureRandom random = new SecureRandom();

    public static void index() {
		List<Posting> postings = Posting.find("order by creationDate desc").fetch();
        render(postings);
    }

	public static void createPosting(String eMail, String displayName, String subject, String description) {
		User user = new User(eMail, displayName).save();
		new Posting(user, Category.FOR_OFFER, subject, description).save();
		render();
	}

	public static String createSecureRandomToken() {
		return new BigInteger(130, random).toString(32);
	}

}