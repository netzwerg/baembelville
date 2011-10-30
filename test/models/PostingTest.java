package models;

import play.mvc.Before;
import play.test.Fixtures;
import play.test.UnitTest;

import org.junit.Test;

import java.util.UUID;

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
		assertEquals(Category.OFFERED, posting.category);
	}

	@Test
	public void tokenHandling() {
		Posting newPosting = createPosting(createSavedUser());
		UUID token = newPosting.token;
		assertNotNull(token);

		newPosting.save();
		Posting foundPosting = Posting.find("byToken", token).first();
		assertNotNull(foundPosting);
		assertEquals(token, foundPosting.token);
	}

	private static Posting createPosting(User user) {
		return new Posting(user, Category.OFFERED, "subject", "description");
	}

	private static User createSavedUser() {
		return new User("bob@gmail.com", "Bob").save();
	}

}