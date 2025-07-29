package hooks;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;

import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStep;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import utils.ExtentManager;

public class ExtentReporterHooks implements ConcurrentEventListener {

	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Before
	public void beforeScenario(Scenario scenario) {
		ExtentTest test = extent.createTest(scenario.getName());
		getExtentTest().set(test);
	}

	@AfterStep

	public void afterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			// Optional: Screenshot on failure if needed
		}
		// all step logs are handled in StepLogger now
	}

	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			getExtentTest().get().log(Status.FAIL, " Scenario Failed: " + scenario.getName());
		} else {
			getExtentTest().get().log(Status.PASS, " Scenario Passed: " + scenario.getName());
		}
	}

	@AfterAll
	public static void tearDownReport() {
		extent.flush();
	}

	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(io.cucumber.plugin.event.TestStepFinished.class, event -> {
			TestStep step = event.getTestStep();
			if (step instanceof PickleStepTestStep) {
				String stepText = ((PickleStepTestStep) step).getStep().getText();
				String keyword = ((PickleStepTestStep) step).getStep().getKeyword();
				CucumberStepHolder.setCurrentStepText(keyword + stepText);
			}
		});
	}

	public static ThreadLocal<ExtentTest> getExtentTest() {
		return extentTest;
	}

	public static void setExtentTest(ThreadLocal<ExtentTest> extentTest) {
		ExtentReporterHooks.extentTest = extentTest;
	}
}
