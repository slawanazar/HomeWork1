package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().creteGroup(new GroupData("test1",null, null));
        }
        app.getContactHelper().createContact(new ContactData("Pasha", "Piter", "+79161221397", "test@yandex.ru", "test1"));
    }

    @Test
    public void testContactCreationSecond() throws Exception {
        app.getContactHelper().createContact(new ContactData("Fedya", "Tula", "+79161221397", "test@yandex.ru", "test5"));
    }
}