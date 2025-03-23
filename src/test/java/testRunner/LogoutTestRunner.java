package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\feature\\Logout.feature",
glue= {"stepDef","hooks"},
plugin = {"pretty","html:target/CucumberReports/report4.html"})



public class LogoutTestRunner extends AbstractTestNGCucumberTests {

}