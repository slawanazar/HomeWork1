package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    protected final AplicationManager app = new AplicationManager(Browser.CHROME);

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void goToEditContact(int i) {
        driver.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void deletingViewingContact() {
        driver.findElement(By.xpath("//div[@id='content']/form[2]/input[2]")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        attach(By.name("photo"), contactData.getPhoto());
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
        driver.findElement(By.name("home")).clear();
        driver.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
        driver.findElement(By.name("mobile")).clear();
        driver.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        driver.findElement(By.name("work")).clear();
        driver.findElement(By.name("work")).sendKeys(contactData.getWorkPhone());
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(contactData.getEmail());
        driver.findElement(By.name("email2")).clear();
        driver.findElement(By.name("email2")).sendKeys(contactData.getSecondEmail());
//        driver.findElement(By.name("email3")).clear();
//        driver.findElement(By.name("email3")).sendKeys(contactData.getThirdEmail());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(driver.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int id) {
//        driver.findElement(By.cssSelector("//input[value='" + id + "']")).click();
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void closeAllert() {
        driver.switchTo().alert().accept();
    }

    public void initContactModification(int id) {
        driver.findElement(By.cssSelector("input[value='" + id + "']")).
                findElement(By.xpath("../..//img[@alt='Edit']")).click();
    }

    public void submitContactModification() {
        driver.findElement(By.name("update")).click();
    }

    public void returnToContactPage() {
        driver.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
        driver.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }


    public void contactPage() {
        driver.findElement(By.linkText("add new")).click();
    }


    public void create(ContactData contact) {
        contactPage();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCash = null;
        returnToContactPage();
    }

    public int Count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCash = null;
    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }
        contactCash = new Contacts();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCash.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return contactCash;
    }


    public ContactData findContactWithGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contactInGroup = contact.getGroups();
            if (contactInGroup.size() > 0) {
                return contact;
            }
        }
        return null;
    }


    public void selectAllGroup() {
        click(By.xpath("(//select[@name='group']/option[text()='[all]'])"));
    }
    public void filterByGroup(int groupId) {
        click(By.xpath("(//select[@name='group']/option[@value='" + groupId + "'])"));
    }
    public void removeContactFromGroup(int contactId, int groupId) {
        filterByGroup(groupId);
        selectContact(contactId);
        removeFromGroup();
    }
    public void removeFromGroup() {
        click(By.xpath("(//input[@name='remove'])"));
    }


    public void modify(ContactData contact) {
        selectContact(contact.getId());
        initContactModification(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteContact();
        closeAllert();
//        app.goTo().homePage();
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");

        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
                .withHomePhone(home).withMobile(mobile).withWorkPhone(work).withEmail(email).withSecondEmail(email2);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public ContactData findContactWithoutGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contactInGroup = contact.getGroups();
            if (contactInGroup.size() == 0) {
                return contact;
            }
        }
        return null;
    }

    public void addContactToGroup(int contactId, int groupId) {
        selectContact(contactId);
        selectGroup(groupId);
        clickAdd();
    }

    private void clickAdd() {
        click(By.xpath("(//input[@name='add'])"));
    }

    private void selectGroup(int Id) {
        click(By.xpath("(//select[@name='to_group']/option[@value='" + Id + "'])"));
    }
}