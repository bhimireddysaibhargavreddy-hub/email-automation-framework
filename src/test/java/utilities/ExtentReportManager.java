
package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            String reportPath =
                    "reports/ExtentReport.html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo(
                    "Project",
                    "Proton Mail Automation"
            );

            extent.setSystemInfo(
                    "Tester",
                    "QA"
            );

            extent.setSystemInfo(
                    "Browser",
                    "Chrome"
            );
        }

        return extent;
    }
}
