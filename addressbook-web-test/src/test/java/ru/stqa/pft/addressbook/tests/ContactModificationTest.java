package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToGroupPage();
        if (app.getGroupHelper().isThereNameGroup("test2")) {
            app.getContactHelper().createContact(new ContactData("Pasha", "Moskow", "+79161221397", "test@yandex.ru", "test2"));
        } else {
            app.getGroupHelper().creteGroup(new GroupData("test2", null, null));
            app.getContactHelper().createContact(new ContactData("Pasha", "Moskow", "+79161221397", "test@yandex.ru", "test2"));
        }
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().goToEditContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Tomsk", "+79191221597", "tester@yandex.ru", "test2"), false);
        app.getContactHelper().submitContactModification();

    }
}