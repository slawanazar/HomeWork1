package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.List;


public class ContactDeletionTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if(app.contact().list().size() == 0){
            app.goTo().groupPage();
            if(app.group().all().size() == 0){
                app.group().create(new GroupData().withName("test2"));
            }
            app.goTo().homePage();
            app.contact().create(new ContactData("Dima", "Ivanov","Moskow", "+79161221397", "test@yandex.ru", "test2"));
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        before.remove(index);
        Assert.assertEquals(before, after);
    }
}