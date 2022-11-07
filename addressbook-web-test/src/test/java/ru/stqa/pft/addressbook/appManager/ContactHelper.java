package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

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