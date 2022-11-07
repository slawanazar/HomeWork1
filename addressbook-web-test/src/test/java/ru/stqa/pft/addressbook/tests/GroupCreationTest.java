package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().creteGroup(new GroupData("Petr", "test5", "test"));
    }

    @Test
    public void testGroupCreationTo() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().creteGroup(new GroupData("Masha", "testik", "Look"));
    }
}