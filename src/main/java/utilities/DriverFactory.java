package utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initializeDriver() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();

        prefs.put(
                "credentials_enable_service",
                false
        );

        prefs.put(
                "profile.password_manager_enabled",
                false
        );

        prefs.put(
                "profile.password_manager_leak_detection",
                false
        );

        options.setExperimentalOption(
                "prefs",
                prefs
        );

        driver.set(new ChromeDriver(options));
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void quitDriver() {

        WebDriver currentDriver = driver.get();

        if (currentDriver != null) {

            currentDriver.quit();

            driver.remove();
        }
    }
}