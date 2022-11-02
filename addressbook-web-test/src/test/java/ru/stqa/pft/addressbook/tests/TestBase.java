package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appManager.AplicationManager;

public class TestBase {

    protected final AplicationManager app = new AplicationManager(Browser.CHROME);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod()
    public void tearDown() {
        app.stop();
    }
}