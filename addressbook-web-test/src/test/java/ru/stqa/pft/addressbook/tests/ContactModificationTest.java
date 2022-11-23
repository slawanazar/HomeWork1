package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test2"));
            }
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Dima").withLastname("Ivanov")
                    .withAddress("Moskow").withMobile("+79161221397").withEmail("test@yandex.ru"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData mofifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(mofifiedContact.getId()).withFirstname("Dima").withLastname("Ivanov")
                .withAddress("Moskow").withMobile("+79161221397").withEmail("test@yandex.ru");

        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.without(mofifiedContact).withAdded(contact)));
    }
}