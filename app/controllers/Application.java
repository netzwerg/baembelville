package controllers;

import models.Category;
import models.Posting;
import models.User;
import play.mvc.Controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

public class Application extends Controller {

    public static void index() {
		List<Posting> postings = Posting.find("order by creationDate desc").fetch();
        render(postings);
    }

	public static void createPosting(String eMail, String displayName, String subject, String description) {
		// TODO: Re-use existing users
		User user = new User(eMail, displayName).save();
		Posting posting = new Posting(user, Category.FOR_OFFER, subject, description);
		// TODO: Prevent duplicate tokens (theoretically impossible, but who knows)
		posting.save();
		render(posting);
	}

	public static void activate(String token) {
		List<Object> postings = Posting.find("byToken", UUID.fromString(token)).fetch();
		if (postings.size() == 1) {
			Posting posting = (Posting) postings.get(0);
			posting.activated = true;
			posting.save();
			render(posting);
		} else {
			// TODO: Handle duplicates
			render();
		}
	}

}