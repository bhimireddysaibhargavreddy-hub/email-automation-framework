
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtils;

public class InboxPage {

//    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(css = "[data-testid='sidebar:compose']")
    private WebElement newMessageButton;

    public InboxPage(WebDriver driver) {

//        this.driver = driver;

        this.waitUtils = new WaitUtils(driver);

        PageFactory.initElements(driver, this);
    }

    public void clickNewMessage() {

        waitUtils.waitForClickable(newMessageButton);

        newMessageButton.click();
    }

    public boolean isNewMessageDisplayed() {

        waitUtils.waitForVisible(newMessageButton);

        return newMessageButton.isDisplayed();
    }
}

