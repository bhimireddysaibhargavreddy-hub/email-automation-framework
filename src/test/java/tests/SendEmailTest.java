package tests;

import java.util.Map;

//import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ComposePage;
import pages.InboxPage;
import pages.SentPage;
import utilities.TestDataProvider;

public class SendEmailTest extends BaseTest {

    // =========================
    // LOGIN ONCE
    // =========================

    @BeforeClass
    public void loginToSendEmailTest() {

        startBrowser();
        login();
    }

    // =========================
    // POSITIVE TEST
    // =========================

    @Test(dataProvider = "sendEmailTestData")
    public void sendEmail(Map<String, String> testData) {

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

        composePage.clickSend();

        SentPage sentPage =
                new SentPage(driver);

        sentPage.clickSentFolder();

//        System.out.println(
//                "Current URL after clicking Sent: "
//                        + driver.getCurrentUrl()
//        );
//
//        System.out.println(
//                "Page title: "
//                        + driver.getTitle()
//        );
//
//        System.out.println(
//                "Subject elements found: "
//                        + driver.findElements(
//                                By.cssSelector(
//                                        "[data-testid='message-column:subject']"
//                                )
//                        ).size()
//        );

        Assert.assertTrue(
                sentPage.isEmailWithSubjectDisplayed(
                        testData.get("Subject")
                ),
                "Email was not found in Sent folder"
        );

    }   // <-- THIS WAS MISSING

    // =========================
    // NEGATIVE TEST
    // =========================

    @Test(dataProvider = "sendEmailNegativeTestData")
    public void sendEmailWithoutRecipient(
            Map<String, String> testData) {

        InboxPage inboxPage =
                new InboxPage(driver);

        inboxPage.clickNewMessage();

        ComposePage composePage =
                new ComposePage(driver);

        // Do NOT enter recipient
        // Do NOT enter subject
        // Do NOT enter body

        composePage.clickSend();

        Assert.assertTrue(
                composePage.isRecipientRequiredMessageDisplayed(),
                "Recipient validation message was not displayed"
        );

        composePage.clickGotIt();
    }

    // =========================
    // CLOSE BROWSER
    // =========================

    @AfterClass
    public void tearDownSendEmailTest() {

        stopBrowser();
    }

    // =========================
    // POSITIVE DATA
    // =========================

    @DataProvider(name = "sendEmailTestData")
    public Object[][] sendEmailTestData() {

        return TestDataProvider.getTestData(
                "SendEmailTest"
        );
    }

    // =========================
    // NEGATIVE DATA
    // =========================

    @DataProvider(name = "sendEmailNegativeTestData")
    public Object[][] sendEmailNegativeTestData() {

        return TestDataProvider.getTestData(
                "SendEmailNegativeTest"
        );
    }
}