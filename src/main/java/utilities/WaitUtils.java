package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void waitForVisible(
            WebElement element) {

        wait.until(
                ExpectedConditions.visibilityOf(
                        element
                )
        );
    }

    public void waitForClickable(
            WebElement element) {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        element
                )
        );
    }
}