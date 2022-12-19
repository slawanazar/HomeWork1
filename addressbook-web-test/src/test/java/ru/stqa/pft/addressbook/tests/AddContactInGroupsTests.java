package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ContactData;

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

//    @Test
//    public void testAddContactInGroups() {
//        Groups groups = app.db().groups();
//        Contacts contacts = app.db().contacts();
//        ContactData selectContact = getSelectContact(contacts, groups.size());
//        GroupData selectGroup = getSelectGroup(groups,selectContact);
//        int beforeAddingGroup = selectContact.getGroups().size();
//        app.contact().addContactToGroup(selectContact, selectGroup);
//        contacts = app.db().contacts();
//        ContactData findContact = getFindContact(contacts, selectContact.getId());
//        int afterAddingGroup = findContact.getGroups().size();
//        assertThat(afterAddingGroup, equalTo(beforeAddingGroup + 1));
//    }
//
//    public ContactData getSelectContact (Contacts contact, int groupsSize){
//        return contact.stream().filter((c) -> c.getGroups().size() != groupsSize).findFirst().get();
//    }
//    public  GroupData getSelectGroup (Groups groupsAll,ContactData contact){
//        groupsAll.removeAll(contact.getGroups());
//        return groupsAll.iterator().next();
//    }
//    private ContactData getFindContact(Contacts contact, int contactId) {
//        return contact.stream().filter((c) -> c.getId() == contactId).findFirst().get();
//    }

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