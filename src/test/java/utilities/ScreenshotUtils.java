package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static String takeScreenshot(
            WebDriver driver,
            String testName) {

        String timestamp =
                new SimpleDateFormat(
                        "yyyyMMdd_HHmmss"
                ).format(new Date());

        String folderPath = "screenshots";

        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath =
                folderPath
                + File.separator
                + testName
                + "_"
                + timestamp
                + ".png";

        File source =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        File destination =
                new File(filePath);

        try {
            FileUtils.copyFile(
                    source,
                    destination
            );
        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to save screenshot",
                    e
            );
        }

        return filePath;
    }
}