package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    //  Method 1: Basic screenshot using FileUtils
    public static String takeScreenshot(WebDriver driver, String name) {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = "screenshots/" + name + "_" + timeStamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(src, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

    //  Method 2: Advanced screenshot with directory creation (used in ExtentReport)
    public static String captureScreenshot(WebDriver driver, String scenarioName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + scenarioName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(screenshotPath);
        try {
            Files.createDirectories(dest.getParentFile().toPath());
            Files.copy(src.toPath(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
}
