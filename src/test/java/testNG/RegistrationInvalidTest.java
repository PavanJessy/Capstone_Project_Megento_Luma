package testNG;

import java.time.Duration;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass1;
import pages.RegistrationInvalidPage;

public class RegistrationInvalidTest extends BaseClass1 {
    private RegistrationInvalidPage createAccountPage;
    
    @BeforeMethod
    @Parameters({"browser", "url"})
    public void setUp(String browser, String url) {
        initialization(browser);
        driver.get(url);
        createAccountPage = new RegistrationInvalidPage(getDriver());
    }
    
    @Test
    @Parameters({"firstname", "lastname", "invalid_email", "password", "confirm_password"})
    public void testCreateAccountWithInvalidCredentials(String firstname, String lastname, String invalid_email, String password, String confirm_password) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(Exception.class);
            
            WebElement createAccountLink = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.linkText("Create an Account"));
                }
            });
            createAccountLink.click();
            
            // Enter invalid credentials
            createAccountPage.enterFirstName(firstname);
            createAccountPage.enterLastName(lastname);
            createAccountPage.enterEmail(invalid_email);
            createAccountPage.enterPassword(password);
            createAccountPage.enterConfirmPassword(confirm_password);
            
            // Click Create Account button
            createAccountPage.clickCreateAccountButton();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}
