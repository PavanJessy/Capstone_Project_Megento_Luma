package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\feature\\UserRegistration.feature",
glue= {"stepDef","hooks"},
plugin = {"pretty","html:target/CucumberReports/report1.html"},
tags="@Signup"
		)
public class UserRegistrationTestRunner extends AbstractTestNGCucumberTests {

}
