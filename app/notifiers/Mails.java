package notifiers;

import models.Posting;
import play.mvc.Mailer;

public class Mails extends Mailer {

    private static final String VERIFICATION_EMAIL_FROM = "Bämbelville <info@baembelville.ch>";
    private static final String VERIFICATION_EMAIL_SUBJECT = "Bestätigung: Neues Inserat auf baembelville.ch";

    public static void verifyPosting(Posting posting) {
        setSubject(VERIFICATION_EMAIL_SUBJECT);
        addRecipient(posting.creator.eMail);
        setFrom(VERIFICATION_EMAIL_FROM);
        send(posting);
    }

}