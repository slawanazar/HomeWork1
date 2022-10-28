package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{
    @Test
    public void testGroupModification(){
        app.getContactHelper().goToEditContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Tomsk", "+79191221597", "tester@yandex.ru"));
        app.getContactHelper().submitContactModification();
    }
}
