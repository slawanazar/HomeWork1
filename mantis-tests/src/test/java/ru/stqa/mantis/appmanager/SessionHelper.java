package ru.stqa.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) throws IOException {
        driver.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }
}