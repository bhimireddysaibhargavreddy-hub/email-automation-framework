package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utilities.TestDataProvider;
import org.testng.annotations.Listeners;
import utilities.TestListener;
import pages.InboxPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    // =========================
    // START BROWSER BEFORE EACH LOGIN TEST
    // =========================

    @BeforeMethod
    public void setUpLoginTest() {

        startBrowser();
    }

    // =========================
    // CLOSE BROWSER AFTER EACH LOGIN TEST
    // =========================

    @AfterMethod
    public void tearDownLoginTest() {

        stopBrowser();
    }

    // =========================
    // VALID LOGIN
    // =========================

    @Test(dataProvider = "loginTestData")
    public void loginTest(Map<String, String> testData) {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(
                testData.get("Username")
        );

        loginPage.enterPassword(
                testData.get("Password")
        );

        loginPage.clickLogin();

        InboxPage inboxPage =
                new InboxPage(driver);

        Assert.assertTrue(
                inboxPage.isNewMessageDisplayed(),
                "New Message button was not displayed after login"
        );
    }

    // =========================
    // WRONG USERNAME
    // =========================

    @Test(dataProvider = "wrongUsernameTestData")
    public void loginWithWrongUsername(
            Map<String, String> testData) {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(
                testData.get("Username")
        );

        loginPage.enterPassword(
                testData.get("Password")
        );

        loginPage.clickLogin();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("account"),
                "Login unexpectedly succeeded with wrong username"
        );
    }

    // =========================
    // WRONG PASSWORD
    // =========================

    @Test(dataProvider = "wrongPasswordTestData")
    public void loginWithWrongPassword(
            Map<String, String> testData) {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(
                testData.get("Username")
        );

        loginPage.enterPassword(
                testData.get("Password")
        );

        loginPage.clickLogin();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("account"),
                "Login unexpectedly succeeded with wrong password"
        );
    }

    // =========================
    // WRONG USERNAME + PASSWORD
    // =========================

    @Test(dataProvider = "wrongBothTestData")
    public void loginWithWrongUsernameAndPassword(
            Map<String, String> testData) {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(
                testData.get("Username")
        );

        loginPage.enterPassword(
                testData.get("Password")
        );

        loginPage.clickLogin();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("account"),
                "Login unexpectedly succeeded with wrong username and password"
        );
    }

    // =========================
    // DATA PROVIDERS
    // =========================

    @DataProvider(name = "loginTestData")
    public Object[][] loginTestData() {

        return TestDataProvider.getTestData(
                "LoginTest"
        );
    }

    @DataProvider(name = "wrongUsernameTestData")
    public Object[][] wrongUsernameTestData() {

        return TestDataProvider.getTestData(
                "LoginWrongUsernameTest"
        );
    }

    @DataProvider(name = "wrongPasswordTestData")
    public Object[][] wrongPasswordTestData() {

        return TestDataProvider.getTestData(
                "LoginWrongPasswordTest"
        );
    }

    @DataProvider(name = "wrongBothTestData")
    public Object[][] wrongBothTestData() {

        return TestDataProvider.getTestData(
                "LoginWrongBothTest"
        );
    }
}