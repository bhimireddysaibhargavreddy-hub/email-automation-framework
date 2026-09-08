package utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentReportManager.getReportInstance();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getName());

        test.set(extentTest);
    }

    // PASS → NO SCREENSHOT
    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
    }

    // FAIL → TAKE SCREENSHOT
    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest baseTest =
                (BaseTest) result.getInstance();

        String screenshotPath =
                ScreenshotUtils.takeScreenshot(
                        baseTest.getDriver(),
                        result.getName() + "_FAIL"
                );

        test.get()
                .fail(result.getThrowable())
                .addScreenCaptureFromPath(
                        screenshotPath
                );
    }

    @Override
    public void onFinish(
            org.testng.ITestContext context) {

        extent.flush();
    }
}

