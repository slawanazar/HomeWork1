package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() throws IOException {
        app.mail().start();
        if (app.db().users().size() == 0) {
            long now = System.currentTimeMillis();
            String mail = String.format("user%s@localhost.localadmin", now);
            String userName = String.format("user%s", now);
            String password = "password";
            app.registration().start(userName, mail);
            List<MailMessage> mailMessageList = app.mail().waitForMail(2, 10000);
            String confirmationLink = app.user().findConfirmationLink(mailMessageList, mail);
            app.registration().finish(confirmationLink, password);
        }
    }

    @Test
    public void changePasswordTest() throws IOException {
        String password = "qwerty";
        Users allUser = app.db().users();
        UserData user = allUser.iterator().next();
        app.user().adminLogIn();
        app.user().initPasswordUpdate(user);
        List<MailMessage> mailMessageList = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.user().findConfirmationLink(mailMessageList, user.getEmail());
        app.user().confirmPasswordUpdateFromEmailLink(user.getUserName(), confirmationLink, password);
        assertTrue(app.newSession().login(user.getUserName(), password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}