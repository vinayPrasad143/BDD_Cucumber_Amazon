package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepdefinitions", "hooks"},
	    tags = "@smoke", // ✅ This avoids loading @OutlineSearch steps
	    plugin = {"pretty", "html:target/cucumber-reports"},
	    monochrome = true
	)


		public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {
		}
