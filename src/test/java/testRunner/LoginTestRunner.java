package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\feature\\UserLogin.feature",
glue= {"stepDef","hooks"},
plugin = {"pretty","html:target/CucumberReports/report2.html"},
tags="@SignIn")


public class LoginTestRunner extends AbstractTestNGCucumberTests {

}
