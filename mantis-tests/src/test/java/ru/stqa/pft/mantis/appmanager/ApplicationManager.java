package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private WebDriver driver;

    private Browser browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private SoapHelper soapHelper;
    private DbHelper dbHelper;
    private UserHelper userHelper;

    public ApplicationManager(Browser browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/local.properties", target))));
        dbHelper = new DbHelper();
        if (browser.equals(Browser.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(Browser.CHROME)) {
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.IE)) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get(properties.getProperty("web.baseUrl"));
    }

    public UserHelper user() {
        if (userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            if (browser.equals(Browser.FIREFOX)) {
                driver = new FirefoxDriver(new FirefoxOptions().setBinary(properties.getProperty("pathFirefox")));
            } else if (browser.equals(Browser.CHROME)) {
                driver = new ChromeDriver();
            } else if (browser.equals(Browser.IE)) {
                driver = new InternetExplorerDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public NavigationHelper navigationHelper() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public NavigationHelper goTo() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }
}