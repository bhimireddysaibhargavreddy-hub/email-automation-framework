package base;

import org.openqa.selenium.WebDriver;

import pages.LoginPage;

import utilities.DriverFactory;
import utilities.ExcelUtils;

public class BaseTest {

    protected WebDriver driver;

    public WebDriver getDriver() {

        return driver;
    }

    protected void startBrowser() {

        DriverFactory.initializeDriver();

        driver = DriverFactory.getDriver();

        driver.manage().window().maximize();

        String url =
                ExcelUtils.getEnvironmentData("URL");

        driver.get(url);
    }

    protected void login() {

        String username =
                ExcelUtils.getEnvironmentData("Username");

        String password =
                ExcelUtils.getEnvironmentData("Password");

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.enterUsername(username);

        loginPage.enterPassword(password);

        loginPage.clickLogin();
    }

    protected void stopBrowser() {

        DriverFactory.quitDriver();
    }
}