package utils;

import com.aventstack.extentreports.Status;
import hooks.ExtentReporterHooks;
import org.openqa.selenium.WebDriver;

public class StepLogger {

    public static void logPass(String stepText) {
        ExtentReporterHooks.getExtentTest().get().log(Status.PASS, "✅ " + stepText);
    }

    public static void logFail(String stepText, Exception e, WebDriver driver) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, stepText.replaceAll("[^a-zA-Z0-9]", "_"));
        ExtentReporterHooks.getExtentTest().get()
            .fail("❌ " + stepText + " — Failed: " + e.getMessage())
            .addScreenCaptureFromPath(screenshotPath);
    }
}
