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
		User user = new User("bob@gmail.com", "Bob").save();
		new Posting(user, "subject", "description").save();
		Posting posting = Posting.find("byCreator", user).first();
		assertNotNull(posting);
		assertEquals(user, posting.creator);
	}

}