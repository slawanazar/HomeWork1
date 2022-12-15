package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroupsTests extends TestBase {

    @BeforeMethod
    public void preconditions() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test16"));
        }
        if (contacts.size() == 0) {
            app.contact().create(new ContactData().withFirstname("Dima").withLastname("Nazar").withAddress("Minsk").withHomePhone("111")
                    .withMobile("222").withWorkPhone("333").withEmail("yandex").withSecondEmail("secondEmail"));
        }
        if (app.contact().findContactWithoutGroup(contacts) == null) {
            ContactData contact = new ContactData().withFirstname("Semen").withLastname("Nazarov").withAddress("Piter").withHomePhone("444")
                    .withMobile("555").withWorkPhone("666").withEmail("gmail").withSecondEmail("secondemailgmail");
            app.contact().create(contact);
            app.goTo().homePage();
        }
    }

    @Test
    public void testAddContactInGroups() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactWithoutGroup = app.contact().findContactWithoutGroup(contacts);
        Integer contactId = contactWithoutGroup.getId();
        GroupData selectedGroup = groups.iterator().next();
        app.contact().addContactToGroup(contactWithoutGroup.getId(), selectedGroup.getID());
        Contacts contactAfter = app.db().getContactById(contactId);
        ContactData contactWithGroup = contactAfter.iterator().next();
        assertThat(contactWithGroup, equalTo(contactWithoutGroup.inGroup(selectedGroup)));
    }
}