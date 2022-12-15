package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoteContactFromGroupsTests extends TestBase {
    @BeforeMethod
    public void preconditions() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test16"));
        }
        if (contacts.size() == 0) {
            app.contact().create(new ContactData().withFirstname("Sergey").inGroup(groups.iterator().next()));
        }
        if (app.contact().findContactWithGroup(contacts) == null) {
            app.goTo().homePage();
            Contacts contactsUI = app.contact().all();
            ContactData selectedContactUI = contactsUI.iterator().next();
            GroupData selectedGroup = groups.iterator().next();
            app.goTo().homePage();
            app.contact().addContactToGroup(selectedContactUI.getId(), selectedGroup.getID());
            app.goTo().homePage();
        }
    }

    @Test
    public void testRemoteContactFromGroups() {
        Contacts contacts = app.db().contacts();
        ContactData contactWithGroup = app.contact().findContactWithGroup(contacts);
        int contactId = contactWithGroup.getId();
        GroupData group = contactWithGroup.getGroups().iterator().next();
        int groupId = group.getID();
        Groups deletedGroup = app.db().getGroupById(groupId);
        GroupData deletedGroupData = deletedGroup.iterator().next();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(contactWithGroup.getId(), group.getID());
        Contacts contactAfter = app.db().getContactById(contactId);
        ContactData contactWithoutGroup = contactAfter.iterator().next();
        assertThat(contactWithGroup, equalTo(contactWithoutGroup.inGroup(deletedGroupData)));
    }
}