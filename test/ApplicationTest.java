import models.Posting;
import models.User;
import org.junit.Before;
import org.junit.Test;
import play.db.jpa.JPABase;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationTest extends FunctionalTest {

    @Before
    public void before() {
        Fixtures.deleteAllModels();
    }

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }

    @Test
    public void testCreatePosting() {
        Response response = createPosting();
        assertIsOk(response);
        List<JPABase> postings = Posting.findAll();
        assertEquals(1, postings.size());
        assertNotNull(((Posting) postings.get(0)).token);
    }

    @Test
    public void testCreatePostingReuseUser() {
        assertEquals(0, User.count());
        createPosting();
        assertEquals(1, User.count());
        createPosting();
        assertEquals(1, User.count());
    }

    @Test
    public void testActivatePosting() {
        assertIsOk(createPosting());
        List<JPABase> postings = Posting.findAll();
        assertEquals(1, postings.size());
        Posting posting = (Posting) postings.get(0);
        assertFalse(posting.activated);
        Request request = newRequest();
        request.params.put("token", String.valueOf(posting.token));
        Response response = GET(request, "/application/activateposting");
        assertIsOk(response);
        posting.refresh();
        assertTrue(posting.activated);
    }

    private Response createPosting() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("eMail", "test@test.com");
        params.put("displayName", "displayName");
        params.put("subject", "subject");
        params.put("description","description");
        return POST("/application/createposting", params);
    }
    
}