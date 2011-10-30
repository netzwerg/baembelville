package controllers;

import models.Category;
import models.Posting;
import models.User;
import notifiers.Mails;
import play.Logger;
import play.mvc.Controller;

import java.util.List;
import java.util.UUID;

public class Application extends Controller {

    public static void listOffered() {
        renderPostingsByCategory(Category.OFFERED);
    }

    public static void listWanted() {
		renderPostingsByCategory(Category.WANTED);
    }

    private static void renderPostingsByCategory(Category category) {
        List<Posting> postings = Posting.find("category = ? order by creationDate", category).fetch();
        render(postings);
    }

	public static void createPosting(String eMail, String displayName, String subject, String description) {
        User user = getOrCreateUser(eMail, displayName);
        // TODO: Wire category as param
		Posting posting = new Posting(user, Category.OFFERED, subject, description);
		posting.save();
        Mails.verifyPosting(posting);
        render(posting);
	}

    private static User getOrCreateUser(String eMail, String displayName) {
        List<Object> existingUsers = User.find("byEMailAndDisplayName", eMail, displayName).fetch();
        if (existingUsers.size() > 0) {
            Logger.debug("Reusing exiting user (eMail [%s], displayName [%s])", eMail, displayName);
            if (existingUsers.size() > 1) {
                Logger.debug("Found %s entries for user (eMail [%s], displayName [%s])", existingUsers.size(), eMail, displayName);
            }
            return (User) existingUsers.get(0);
        } else {
            return new User(eMail, displayName).save();
        }
    }

    public static void deletePosting(String token) {
        // TODO: Implement
    }

    public static void activatePosting(String token) {
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