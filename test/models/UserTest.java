package models;

import play.mvc.Before;
import play.test.Fixtures;
import play.test.UnitTest;

import org.junit.Test;

public class UserTest extends UnitTest {

	@Before
    public void setup() {
        Fixtures.deleteAllModels();
    }

	@Test
	public void createAndRetrieveUser() {
		new User("bob@gmail.com", "Bob").save();
		User bob = User.find("byEmail", "bob@gmail.com").first();
		assertNotNull(bob);
		assertEquals("Bob", bob.displayName);
	}

}