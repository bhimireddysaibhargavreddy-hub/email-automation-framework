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

public class BodyTest extends BaseTest {

    // LOGIN ONCE
    @BeforeClass
    public void loginToBodyTest() {
        startBrowser();
        login();
    }

    // POSITIVE TEST
    @Test(dataProvider = "bodyTestData")
    public void verifyBody(Map<String, String> testData) {

        InboxPage inboxPage =
                new InboxPage(driver);

        inboxPage.clickNewMessage();

        ComposePage composePage =
                new ComposePage(driver);

        composePage.enterRecipient(
                testData.get("Recipient")
        );

        composePage.enterSubject(
                testData.get("Subject")
        );

        composePage.enterBody(
                testData.get("Body")
        );

        Assert.assertTrue(
                testData.get("Body") != null
                        && !testData.get("Body").isEmpty(),
                "Body was not entered"
        );
    }

    // NEGATIVE TEST
    @Test(dataProvider = "bodyNegativeTestData")
    public void verifyBodyValidation(
            Map<String, String> testData) {

        InboxPage inboxPage =
                new InboxPage(driver);

        inboxPage.clickNewMessage();

        ComposePage composePage =
                new ComposePage(driver);

        composePage.enterRecipient(
                testData.get("Recipient")
        );

        composePage.enterSubject(
                testData.get("Subject")
        );

        // Do NOT enter body
        // Proton Mail allows an empty body

        composePage.clickSend();

//        System.out.println(
//                "After clicking Send, current URL: "
//                + driver.getCurrentUrl()
//        );
    }

    // CLOSE BROWSER
    @AfterClass
    public void tearDownBodyTest() {
        stopBrowser();
    }

    // POSITIVE DATA
    @DataProvider(name = "bodyTestData")
    public Object[][] bodyTestData() {

        return TestDataProvider.getTestData(
                "BodyTest"
        );
    }

    // NEGATIVE DATA
    @DataProvider(name = "bodyNegativeTestData")
    public Object[][] bodyNegativeTestData() {

        return TestDataProvider.getTestData(
                "BodyNegativeTest"
        );
    }
}