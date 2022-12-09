package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }
    public void login(String login, String password, String url) {
        driver.get(url);
        type(By.name("user"), login);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}