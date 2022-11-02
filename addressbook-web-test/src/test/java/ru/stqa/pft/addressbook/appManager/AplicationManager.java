package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;

public class AplicationManager {
    WebDriver driver;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private Browser browser;

    public AplicationManager(Browser browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(Browser.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.IE)) {
            driver = new InternetExplorerDriver();
        }


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("http://localhost/addressbook/group.php");
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        driver.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}