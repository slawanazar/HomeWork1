package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTest extends TestBase {
    @Test
    public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().isThereNameGroup("test2")) {
            app.getNavigationHelper().goToHomePage();
        } else {
            app.getGroupHelper().creteGroup(new GroupData("test2", null, null));
            app.getNavigationHelper().goToHomePage();
        }
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Vyacheslav", "Moscow", "+79161221397", "test@yandex.ru", "test2"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().closeAllert();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}