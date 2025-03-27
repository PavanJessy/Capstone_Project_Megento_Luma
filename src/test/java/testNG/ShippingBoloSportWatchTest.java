package testNG;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class ShippingBoloSportWatchTest{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");

        // Login
        driver.findElement(By.id("email")).sendKeys("pavan.anantha@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Pass@1234");
        driver.findElement(By.id("send2")).click();

        // Verify login success
        Assert.assertTrue(driver.getCurrentUrl().contains("customer/account"), "Login failed!");
    }
    
    @Test(priority = 0)
    public void clearCartIfNotEmpty() {
        driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
        
        try {
            // If the cart has items, remove them
            while (driver.findElements(By.cssSelector(".action-delete")).size() > 0) {
                WebElement deleteButton = driver.findElement(By.cssSelector(".action-delete"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
                wait.until(ExpectedConditions.invisibilityOf(deleteButton));
            }
            System.out.println("Cart cleared before test.");
        } catch (Exception e) {
            System.out.println("No items in cart or failed to clear cart: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void user_searches_for_bolo_sport_watch() {
        driver.get("https://magento.softwaretestingboard.com/");
        
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
        searchBox.sendKeys("Bolo Sport Watch", Keys.ENTER);

        wait.until(ExpectedConditions.titleContains("Bolo Sport Watch"));

        // Validate search results page
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Bolo Sport Watch"), "Search Page Title Validation Failed!");

        System.out.println("Successfully searched and found Bolo Sport Watch.");
    }
    
    @Test(priority = 2)
    public void user_adds_bolo_sport_watch_to_cart() {
        driver.get("https://magento.softwaretestingboard.com/bolo-sport-watch.html");

        // Add to cart using JavaScript in case of overlay issues
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);

        // Wait for success message using explicit wait
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'message-success')]")));
        Assert.assertTrue(successMessage.isDisplayed(), "Failed to add product to cart!");

        // Wait for cart count to update properly
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".counter-number"), "1"));
        
        WebElement cartCounter = driver.findElement(By.cssSelector(".counter-number"));
        Assert.assertEquals(Integer.parseInt(cartCounter.getText()), 1, "Cart count doesn't match!");

        System.out.println("Successfully added Bolo Sport Watch to cart.");
    }


    
    @Test(priority = 3)
    public void user_views_and_edits_cart() {
        driver.findElement(By.cssSelector(".showcart")).click();
        driver.findElement(By.linkText("View and Edit Cart")).click();

        // Verify cart page
        wait.until(ExpectedConditions.titleContains("Shopping Cart"));
        System.out.println("Viewed and edited the cart.");
    }

    @Test(priority = 4)
    public void user_proceeds_to_checkout() {
        driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']")).click();

        // Wait for checkout page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#shipping")));
        System.out.println("Proceeded to checkout.");
    }

    @Test(priority = 5)
    public void user_enters_shipping_details() {
        try {
            // Enter name
            driver.findElement(By.cssSelector("[name='firstname']")).sendKeys("Anantha");
            driver.findElement(By.cssSelector("[name='lastname']")).sendKeys("Pavan");

            // Enter address
            driver.findElement(By.cssSelector("[name='street[0]']")).sendKeys("123 Elm Street");
            driver.findElement(By.cssSelector("[name='city']")).sendKeys("Los Angeles");

            // Select State/Region
            WebElement regionDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("[name='region_id']")));
            new Select(regionDropdown).selectByVisibleText("California");

            // Enter ZIP Code
            driver.findElement(By.cssSelector("[name='postcode']")).sendKeys("90001");

            // Select Country
            WebElement countryDropdown = driver.findElement(By.cssSelector("[name='country_id']"));
            new Select(countryDropdown).selectByVisibleText("United States");

            // Enter Phone Number
            driver.findElement(By.cssSelector("[name='telephone']")).sendKeys("6301779222");

            // Select Shipping Method
            WebElement shippingMethod = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("input[name='ko_unique_1']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shippingMethod);

            screenshot("before_next_click");

            // Click Next
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".button.action.continue.primary")));
            nextButton.click();

            // Verify payment page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#payment")));
        } catch (Exception e) {
            screenshot("shipping_details_error");
            Assert.fail("Failed to enter shipping details: " + e.getMessage());
        }
    }
    
    @Test(priority = 6)
    public void user_places_the_order() {
        try {
            // Ensure loading mask is gone
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));

            // Retry clicking the button in case of overlay issues
            WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'checkout') and contains(@title,'Place Order')]")));
            for (int i = 0; i < 3; i++) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderBtn);
                    break;
                } catch (Exception e) {
                    System.out.println("Retry clicking Place Order button: Attempt " + (i + 1));
                    Thread.sleep(1000);
                }
            }

            // Wait for success message with extended timeout
            WebElement successMessage = wait.withTimeout(Duration.ofSeconds(20))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(text(),'Thank you for your purchase!')]")));
            Assert.assertTrue(successMessage.isDisplayed(), "Order placement failed!");

        } catch (Exception e) {
            screenshot("order_failure");
            Assert.fail("Error while placing the order: " + e.getMessage());
        }
    }

    

    
    @Test(priority = 7)
    public void user_logs_out() {
        try {
            WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='panel header']//button[@type='button']")));
            accountDropdown.click();

            WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@aria-hidden='false']//a[contains(text(),'Sign Out')]")));
            logoutLink.click();

            // Verify logout success
            wait.until(ExpectedConditions.urlContains("logoutSuccess"));
        } catch (Exception e) {
            Assert.fail("Error while logging out: " + e.getMessage());
        }
    }

    private void screenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("screenshots/" + name + "_" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
