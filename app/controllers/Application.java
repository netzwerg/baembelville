package controllers;

import models.Posting;
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

	public static String createSecureRandomToken() {
		return new BigInteger(130, random).toString(32);
	}

}