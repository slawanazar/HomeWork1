package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class AplicationManager {
    private final Properties properties;
    WebDriver driver;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private Browser browser;
    private DbHelper dbhelper;

    public AplicationManager(Browser browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties", target))));
        dbhelper = new DbHelper();
        if ("".equals(properties.getProperty("senenium.server"))) {
            if (browser.equals(Browser.FIREFOX)) {
                driver = new FirefoxDriver();
            } else if (browser.equals(Browser.CHROME)) {
                driver = new ChromeDriver();
            } else if (browser.equals(Browser.IE)) {
                driver = new InternetExplorerDriver();
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(String.valueOf(browser));
            driver = new RemoteWebDriver(new URL(properties.getProperty("senenium.server")), capabilities);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        contactHelper = new ContactHelper(driver);
        sessionHelper.login(properties.getProperty("web.adminLogin"),
                properties.getProperty("web.adminPassword"), properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        driver.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbhelper;
    }
}