package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().createContact(new ContactData("Pasha", "Piter", "+79161221397", "test@yandex.ru", "Petr"));
    }

    @Test
    public void testContactCreationSecond() throws Exception {
        app.getContactHelper().createContact(new ContactData("Fedya", "Tula", "+79161221397", "test@yandex.ru", "Masha"));
    }
}