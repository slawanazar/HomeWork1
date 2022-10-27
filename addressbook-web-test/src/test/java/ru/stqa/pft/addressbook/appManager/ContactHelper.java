package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper {
    WebDriver driver;

    public ContactHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void goToEditContact()  {
        driver.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void deletingViewingContact() {
        driver.findElement(By.xpath("//div[@id='content']/form[2]/input[2]")).click();
    }

    public void fillContactForm(ContactDate contactDate) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(contactDate.getFirstName());
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(contactDate.getAddress());
        driver.findElement(By.name("mobile")).clear();
        driver.findElement(By.name("mobile")).sendKeys(contactDate.getMobile());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(contactDate.getEmail());
    }

    public void submitContactModification() {
        driver.findElement(By.name("update")).click();
    }
}
