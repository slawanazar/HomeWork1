package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase{
    @Test
    public void testGroupModification(){
        app.getContactHelper().goToEditContact();
        app.getContactHelper().fillContactForm(new ContactDate("Ivan", "Tomsk", "+79191221597", "tester@yandex.ru"));
        app.getContactHelper().submitContactModification();
    }
}
