package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InboxPage;

public class InboxTest extends BaseTest {

    @BeforeClass
    public void loginToInbox() {

        startBrowser();

        login();
    }

    @Test
    public void openNewMessage() {

        InboxPage inboxPage =
                new InboxPage(driver);

        inboxPage.clickNewMessage();
    }

    @AfterClass
    public void tearDownInbox() {

        stopBrowser();
    }
}