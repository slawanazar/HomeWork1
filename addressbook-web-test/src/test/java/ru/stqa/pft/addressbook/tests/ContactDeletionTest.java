package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTest extends TestBase {
        @Test
        public void testContactDeletion() throws Exception {
            app.getNavigationHelper().goToHomePage();
            if(! app.getContactHelper().isThereAContact()){
                app.getContactHelper().createContact(new ContactData("Vyacheslav", "Moscow", "+79161221397", "test@yandex.ru", "test1"));
            }
            app.getContactHelper().goToEditContact();
            app.getContactHelper().deletingViewingContact();
        }
    }