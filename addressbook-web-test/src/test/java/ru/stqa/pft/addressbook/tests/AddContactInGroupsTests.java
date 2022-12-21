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
        int contactAll = 0;
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() == groups.size()) {
                contactAll++;
            }
            if (contactAll == contacts.size()) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test16"));
                app.goTo().homePage();
            }
        }
    }

    @Test
    public void testAddContactInGroups() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactWithoutGroup = getSelectContact(contacts, groups.size());
        GroupData selectGroup = getSelectGroup(groups, contactWithoutGroup);
        int beforeAddToGroup = contactWithoutGroup.getGroups().size();
        app.contact().addToGroup(contactWithoutGroup, selectGroup);
        contacts = app.db().contacts();
        ContactData findContact = getFindContact(contacts, contactWithoutGroup.getId());
        int afterAddToGroup = findContact.getGroups().size();
        assertThat(afterAddToGroup, equalTo(beforeAddToGroup + 1));
    }

    public ContactData getSelectContact(Contacts contact, int groupsSize) {
        return contact.stream().filter((c) -> c.getGroups().size() != groupsSize).findFirst().get();
    }

    public GroupData getSelectGroup(Groups groupsAll, ContactData contact) {
        groupsAll.removeAll(contact.getGroups());
        return groupsAll.iterator().next();
    }

    private ContactData getFindContact(Contacts contact, int contactId) {
        return contact.stream().filter((c) -> c.getId() == contactId).findFirst().get();
    }
}