package ru.stqa.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordTests extends TestBase {

    @Test
    public void testChangeUserPassword() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost", now);
        app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 10000);
        String confirmationLink = findConfirmationLinkCreateUser(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        app.session().login("administrator", "root");
        app.navigationHelper().goToManageUsersPage();
        app.navigationHelper().selectUser(user);
        app.navigationHelper().resetPassword();
        List<MailMessage> mailMessagesResetPassword = app.james().waitForMailMoreOne(user, password, 120000);
        String confirmationLinkResetPassword = findConfirmationLink(mailMessagesResetPassword, email);
        String newPassword = "pas14";
        app.registration().finish(confirmationLinkResetPassword, newPassword);
        app.session().login(user, newPassword);
        assertTrue(app.newSession().login(user, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email) && m.text.contains("Someone (presumably you) requested a password change through e-mail")).iterator().next();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    private String findConfirmationLinkCreateUser(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}