package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

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
//        app.getNavigationHelper().goToHomePage();
//        app.getContactHelper().goToEditContact(before.size() - 1);
//        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Tomsk", "+79191221597", "tester@yandex.ru", "test2"), false);
//        app.getContactHelper().submitContactModification();

        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Pasha", "Moskow", "+79161221397", "test@yandex.ru", "test2");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}