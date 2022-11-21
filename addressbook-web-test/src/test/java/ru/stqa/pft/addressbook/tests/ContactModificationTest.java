package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if(app.contact().list().size() == 0){
            app.goTo().groupPage();
            if(app.group().list().size() == 0){
                app.group().create(new GroupData().withName("test2"));
            }
            app.goTo().homePage();
            app.contact().create(new ContactData("Dima", "Ivanov","Moskow", "+79161221397", "test@yandex.ru", "test2"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(),"Vova", "Ivanov","Moskow", "+79161221397", "test@yandex.ru");
        app.contact().modify(contact, index);
        List<ContactData> after = app.contact().list();
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}