package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Vyacheslav", "Moscow", "+79161221397", "test@yandex.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
    }

    @Test
    public void testContactCreationSecond() throws Exception {
        app.getContactHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Dima", "Omsk", "+79191221597", "test@yandex.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
    }
}