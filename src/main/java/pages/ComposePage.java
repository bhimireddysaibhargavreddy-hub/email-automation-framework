
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtils;

public class ComposePage {

    private WebDriver driver;

    private WaitUtils waitUtils;

    @FindBy(css = "[data-testid='composer:to']")
    private WebElement toField;

    @FindBy(css = "[data-testid='composer:subject']")
    private WebElement subjectField;

    @FindBy(css = "[data-testid='rooster-iframe']") private WebElement bodyIframe; @FindBy(id = "rooster-editor") private WebElement bodyField;
    

    @FindBy(css = "[data-testid='composer:send-button']")
    private WebElement sendButton;

    // Recipient validation popup
    @FindBy(xpath =
            "//p[normalize-space()='Please add at least one recipient.']")
    private WebElement recipientRequiredMessage;

    @FindBy(css =
            "[data-testid='modal-footer:set-button']")
    private WebElement gotItButton;

    @FindBy(xpath =
            "//form[contains(@class,'inner-modal-content')]//p[contains(text(),'You have not given your email any subject')]")
    private WebElement subjectValidationMessage;

    @FindBy(css =
            "[data-testid='modal-footer:cancel-button']")
    private WebElement cancelButton;


    public ComposePage(WebDriver driver) {

        this.driver = driver;

        this.waitUtils = new WaitUtils(driver);

        PageFactory.initElements(driver, this);
    }


    // Enter recipient
    public void enterRecipient(String email) {

        waitUtils.waitForVisible(toField);

        toField.click();

        toField.sendKeys(email);
    }


    // Enter subject
    public void enterSubject(String subject) {

        waitUtils.waitForVisible(subjectField);

        subjectField.sendKeys(subject);
    }

    public void enterBody(String body) { 
    	waitUtils.waitForVisible(bodyIframe); driver.switchTo().frame(bodyIframe); 
    	waitUtils.waitForVisible(bodyField); bodyField.sendKeys(body); driver.switchTo().defaultContent(); }

    // Click Send
    public void clickSend() {

        driver.switchTo().defaultContent();

        waitUtils.waitForClickable(sendButton);

        sendButton.click();
    }

    // Check recipient validation message
    public boolean isRecipientRequiredMessageDisplayed() {

        waitUtils.waitForVisible(recipientRequiredMessage);

        return recipientRequiredMessage.isDisplayed();
    }


    // Close recipient validation popup
    public void clickGotIt() {

        waitUtils.waitForClickable(gotItButton);

        gotItButton.click();
    }


    // Check subject validation message
    public boolean isSubjectValidationMessageDisplayed() {

        driver.switchTo().defaultContent();

        waitUtils.waitForVisible(subjectValidationMessage);

        return subjectValidationMessage.isDisplayed();
    }


    // Close subject validation popup
    public void clickCancel() {

        waitUtils.waitForClickable(cancelButton);

        cancelButton.click();
    }
}

