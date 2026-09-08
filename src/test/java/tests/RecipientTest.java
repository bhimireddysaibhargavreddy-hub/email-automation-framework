package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ComposePage;
import pages.InboxPage;
import utilities.TestDataProvider;

public class RecipientTest extends BaseTest {

    // LOGIN ONCE
    @BeforeClass
    public void loginToRecipientTest() {

        startBrowser();
        login();
    }

    // POSITIVE TEST
    @Test(dataProvider = "recipientTestData")
    public void verifyRecipient(Map<String, String> testData) {

        InboxPage inboxPage = new InboxPage(driver);
        inboxPage.clickNewMessage();

        ComposePage composePage = new ComposePage(driver);

        composePage.enterRecipient(
                testData.get("Recipient")
        );

        Assert.assertTrue(
                testData.get("Recipient") != null
                        && !testData.get("Recipient").isEmpty(),
                "Recipient was not entered"
        );
    }

    // NEGATIVE TEST
    @Test(dataProvider = "recipientNegativeTestData")
    public void verifyInvalidRecipient(
            Map<String, String> testData) {

        InboxPage inboxPage = new InboxPage(driver);
        inboxPage.clickNewMessage();

        ComposePage composePage = new ComposePage(driver);

        // Do NOT enter recipient
        composePage.clickSend();

        Assert.assertTrue(
                composePage.isRecipientRequiredMessageDisplayed(),
                "Recipient validation message was not displayed"
        );

        composePage.clickGotIt();
    }

    // CLOSE BROWSER
    @AfterClass
    public void tearDownRecipientTest() {

        stopBrowser();
    }

    // POSITIVE DATA
    @DataProvider(name = "recipientTestData")
    public Object[][] recipientTestData() {

        return TestDataProvider.getTestData(
                "RecipientTest"
        );
    }

    // NEGATIVE DATA
    @DataProvider(name = "recipientNegativeTestData")
    public Object[][] recipientNegativeTestData() {

        return TestDataProvider.getTestData(
                "RecipientNegativeTest"
        );
    }
}