package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\feature\\ProductSearchAddToCart.feature",
glue= {"stepDef","hooks"},
plugin = {"pretty","html:target/CucumberReports/report3.html"},
tags="@AddToCart" )

public class ProductSearchAddToCartTestRunner extends AbstractTestNGCucumberTests{

}


