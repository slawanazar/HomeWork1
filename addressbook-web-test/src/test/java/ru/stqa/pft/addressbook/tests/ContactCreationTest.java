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
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("Pasha", "Ivanov", "Moskow", "+79161221397", "test@yandex.ru", "test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        before.add(contact);
        Comparator<? super ContactData> byId = (v1, v2) -> Integer.compare(v1.getId(), v2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}