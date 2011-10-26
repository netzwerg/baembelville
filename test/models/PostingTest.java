package models;

import play.mvc.Before;
import play.test.Fixtures;
import play.test.UnitTest;

import org.junit.Test;

public class PostingTest extends UnitTest {

	@Before
    public void setup() {
        Fixtures.deleteAllModels();
    }

	@Test
	public void createAndRetrievePosting() {
		User user = createSavedUser();
		createPosting(user).save();
		Posting posting = Posting.find("byCreator", user).first();
		assertNotNull(posting);
		assertEquals(user, posting.creator);
		assertEquals(Category.FOR_OFFER, posting.category);
	}

	@Test
	public void tokenHandling() {
		Posting newPosting = createPosting(createSavedUser());
		String token = newPosting.token;
		assertNotNull(token);

		newPosting.save();
		Posting foundPosting = Posting.find("byToken", token).first();
		assertNotNull(foundPosting);
		assertEquals(token, foundPosting.token);
	}

	private static Posting createPosting(User user) {
		return new Posting(user, Category.FOR_OFFER, "subject", "description");
	}

	private static User createSavedUser() {
		return new User("bob@gmail.com", "Bob").save();
	}

}