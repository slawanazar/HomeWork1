package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereNameGroup("test300")) {
            app.getGroupHelper().creteGroup(new GroupData("test300",null, null));
        }
        app.getContactHelper().createContact(new ContactData("Pasha", "Piter", "+79161221397", "test@yandex.ru", "test300"));
    }

    @Test
    public void testContactCreationSecond() throws Exception {
        app.getContactHelper().createContact(new ContactData("Fedya", "Tula", "+79161221397", "test@yandex.ru", "test5"));
    }
}