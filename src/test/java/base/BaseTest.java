package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {

        String browserName = ConfigReader.getProperty("browser");

        switch (browserName.toLowerCase()) {
            case "chrome":
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless=new"); // 🆕 Use new headless mode
//                options.addArguments("--no-sandbox");
//                options.addArguments("--disable-dev-shm-usage");
//                options.addArguments("--disable-gpu");
//                options.addArguments("--remote-allow-origins=*");
//                options.addArguments("--window-size=1920,1080"); // 🆕 ensure layout loads correctly
                driver = new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                System.out.println("❌ Invalid browser name in config.properties!");
                throw new RuntimeException("Unsupported browser");
        }

        // Only maximize if not headless (maximizing in headless sometimes throws)
        if (!browserName.equalsIgnoreCase("chrome") || !driver.toString().contains("headless")) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
