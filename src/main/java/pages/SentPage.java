package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentPage {

    private WebDriver driver;

    private WebDriverWait wait;

    // =========================
    // SENT FOLDER
    // =========================

    @FindBy(
        css = "span[title='Sent [G] [E]']"
    )
    private WebElement sentFolder;

    // =========================
    // CONSTRUCTOR
    // =========================

    public SentPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(15)
                );

        PageFactory.initElements(
                driver,
                this
        );
    }

    // =========================
    // CLICK SENT
    // =========================

    public void clickSentFolder() {

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(sentFolder)
        );

        sentFolder.click();
    }

    // =========================
    // VERIFY EMAIL SUBJECT
    // =========================

    public boolean isEmailWithSubjectDisplayed(
            String expectedSubject) {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(30)
                );

        return wait.until(driver -> {

            List<WebElement> elements =
                    driver.findElements(
                            By.xpath(
                                    "//span[normalize-space()='"
                                    + expectedSubject
                                    + "']"
                            )
                    );

            System.out.println(
                    "Subject found: "
                            + elements.size()
            );

            return !elements.isEmpty();
        });
    }
}