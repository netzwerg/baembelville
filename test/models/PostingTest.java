package models;

import org.junit.Test;
import play.mvc.Before;
import play.test.Fixtures;
import play.test.UnitTest;

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
	public void idHandling() {
		Posting newPosting = createPosting(createSavedUser());
		newPosting.save();
		String id = newPosting.id;
		assertNotNull(id);
		Posting foundPosting = Posting.find("byId", id).first();
		assertNotNull(foundPosting);
		assertEquals(id, foundPosting.id);
	}

	private static Posting createPosting(User user) {
		return new Posting(user, Category.OFFERED, "subject", "description");
	}

	private static User createSavedUser() {
		return new User("bob@gmail.com", "Bob").save();
	}

}