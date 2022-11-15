package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().creteGroup(new GroupData("test1", null, null));
        }
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Pasha", "Moskow", "+79161221397", "test@yandex.ru", "test1");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        before.add(contact);
        Comparator<? super ContactData> byId = (v1, v2) -> Integer.compare(v1.getId(), v2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}