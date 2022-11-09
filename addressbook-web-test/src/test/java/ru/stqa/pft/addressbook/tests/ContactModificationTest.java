package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{
    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Vyacheslav", "Moscow", "+79161221397", "test@yandex.ru", "sezh4rg"));
        }
        app.getContactHelper().goToEditContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Tomsk", "+79191221597", "tester@yandex.ru", "sezh4rg"), false);
        app.getContactHelper().submitContactModification();
    }
}