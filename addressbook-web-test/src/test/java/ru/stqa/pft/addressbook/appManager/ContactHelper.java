package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {
    protected final AplicationManager app = new AplicationManager(Browser.CHROME);

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void goToEditContact() {
        driver.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void deletingViewingContact() {
        driver.findElement(By.xpath("//div[@id='content']/form[2]/input[2]")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        driver.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
        driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
        driver.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        driver.findElement(By.name("email")).sendKeys(contactData.getEmail());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

//    public void groupSearch(String nameGroup, String her, ContactData contactData) {
////        goToContactPage();
//        if (isElementPresent(By.name(contactData.getGroup()))) {
//            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        } else {
//            app.getNavigationHelper().goToGroupPage();
////            List<GroupData> before = app.getGroupHelper().getGroupList();
//            app.getGroupHelper().creteGroup(new GroupData(contactData.getGroup(), null, null));
//            List<GroupData> after = app.getGroupHelper().getGroupList();
//            Assert.assertEquals(after.size(), before.size() +1);

//        }
//    }

    public void submitContactModification() {
        driver.findElement(By.name("update")).click();
    }

    public void returnToContactPage() {
        driver.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
        driver.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void goToContactPage() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void createContact(ContactData contact) {
        goToContactPage();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }
}