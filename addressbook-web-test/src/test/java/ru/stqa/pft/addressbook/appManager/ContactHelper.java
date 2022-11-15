package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.List;

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
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(contactData.getAddress());
        driver.findElement(By.name("mobile")).clear();
        driver.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(contactData.getEmail());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void closeAllert() {
        driver.switchTo().alert().accept();
    }

    public void initContactModification(int index) {
        driver.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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

    public List<ContactData> getContactList() {
        ArrayList<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            ContactData contact = new ContactData(id, firstname, lastname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}