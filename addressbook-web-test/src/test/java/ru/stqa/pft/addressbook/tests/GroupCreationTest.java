package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().creteGroup(new GroupData("Petr", null, null));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() +1);
    }

//    @Test
//    public void testGroupCreationTo() {
//        app.getNavigationHelper().goToGroupPage();
//        int before = app.getGroupHelper().getGroupCount();
//        app.getGroupHelper().creteGroup(new GroupData("Masha", "testik", "Look"));
//        int after = app.getGroupHelper().getGroupCount();
//        Assert.assertEquals(after, before +1);
//    }
}