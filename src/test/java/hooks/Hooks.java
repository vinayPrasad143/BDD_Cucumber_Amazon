package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtil;

public class Hooks extends BaseTest {

	@Before
	public void openBrowser() {
		setUp();

	}

	@After
	public void closeBrowser(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName());

			System.out.println("Scenario failed: " + scenario.getName());
			System.out.println("Failed Screenshot path: " + screenshotPath);
		}

		tearDown();

	}

}
