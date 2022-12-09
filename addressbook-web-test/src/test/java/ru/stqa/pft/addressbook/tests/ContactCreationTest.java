package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTest extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            xStream.allowTypes(new Class[]{ContactData.class});
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test2").withHeader("test").withFooter("test"));
        }
    }

    @Test(dataProvider = "validContactsFromXml")
    public void testContactCreation(ContactData contact) throws Exception {
//        app.goTo().groupPage();
//        if (app.group().all().size() == 0){
//            app.group().create(new GroupData().withName("test1"));
//        }
        app.goTo().homePage();
        Contacts before = app.db().contacts();
//        File photo = new File("src/test/resources/stru.png");
//        ContactData contact = new ContactData().withFirstname("Slava").withLastname("Nazar")
//                .withAddress("Sochi").withMobile("89152538869").withEmail("slava@yandex.ru").withWorkPhone("8 915 253 8869").withHomePhone("8-915-253-8869").withSecondEmail("slava@yandex.ru").withPhoto(photo);
//                , true);
        app.contact().create(contact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }
}