package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManageUsersPage() {
        click((By.xpath("//a[text()='Manage']")));
        click((By.xpath("//a[text()='Manage Users']")));
    }

    public void selectUser(String username) {
        click((By.xpath("//a[text()='" + username + "']")));
    }

    public void resetPassword() {
        click((By.xpath("//input[@value='Reset Password']")));
    }

    public void logout() {
        click((By.xpath("//a[text()='Logout']")));
    }

    private void selectUser(int id) {
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
    }
}