package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitUtils;

public class LoginPage {

//    private WebDriver driver;
    private WaitUtils waitUtils;

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {

//        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);

        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {

        waitUtils.waitForVisible(usernameField);
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {

        waitUtils.waitForVisible(passwordField);
        passwordField.sendKeys(password);
    }

    public void clickLogin() {

        waitUtils.waitForClickable(loginButton);
        loginButton.click();
    }

    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}