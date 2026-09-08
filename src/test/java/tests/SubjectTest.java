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

public class SubjectTest extends BaseTest {

    // =========================
    // LOGIN ONCE
    // =========================

    @BeforeClass
    public void loginToSubjectTest() {

        startBrowser();

        login();
    }

    // =========================
    // POSITIVE TEST
    // =========================

    @Test(dataProvider = "subjectTestData")
    public void verifySubject(Map<String, String> testData) {

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

        Assert.assertTrue(
                testData.get("Subject") != null
                        && !testData.get("Subject").isEmpty(),
                "Subject was not entered"
        );

        // Refresh browser after positive test
        driver.navigate().refresh();
    }

    // =========================
    // NEGATIVE TEST
    // =========================

    @Test(
        dataProvider = "subjectNegativeTestData",
        dependsOnMethods = "verifySubject"
    )
    public void verifySubjectValidation(
            Map<String, String> testData) {

        InboxPage inboxPage =
                new InboxPage(driver);

        inboxPage.clickNewMessage();

        ComposePage composePage =
                new ComposePage(driver);

        composePage.enterRecipient(
                testData.get("Recipient")
        );

        // Do NOT enter subject

        composePage.clickSend();

        Assert.assertTrue(
                composePage.isSubjectValidationMessageDisplayed(),
                "Subject validation message was not displayed"
        );

        composePage.clickCancel();
    }

    // =========================
    // CLOSE BROWSER
    // =========================

    @AfterClass
    public void tearDownSubjectTest() {

        stopBrowser();
    }

    // =========================
    // POSITIVE DATA
    // =========================

    @DataProvider(name = "subjectTestData")
    public Object[][] subjectTestData() {

        return TestDataProvider.getTestData(
                "SubjectTest"
        );
    }

    // =========================
    // NEGATIVE DATA
    // =========================

    @DataProvider(name = "subjectNegativeTestData")
    public Object[][] subjectNegativeTestData() {

        return TestDataProvider.getTestData(
                "SubjectNegativeTest"
        );
    }
}