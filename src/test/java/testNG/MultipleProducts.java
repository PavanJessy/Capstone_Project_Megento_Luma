package testNG;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.WebDriver;

public class MultipleProducts {
	
	//Class level webdriver
	  WebDriver driver;
	    WebDriverWait wait;

	    @BeforeTest
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html");
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }
	    
	    @Test
	    public void testAddRandomMenHoodiesToCartAndCheckout() {
	        try {
	            int numberOfProductsToAdd = 3; // Adjust as needed

	            for (int i = 0; i < numberOfProductsToAdd; i++) {
	                selectRandomProductAndAddToCart();
	            }

	            openCart();
	            viewAndEditCart();
	            removeOneProductFromCart();
	            checkoutButton();
	           
	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	            Assert.fail("Test Failed: " + e.getMessage());
	        } finally {
	            driver.quit();
	        }
	    }

	    public void selectRandomProductAndAddToCart() throws InterruptedException {
//	        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//	                By.xpath("//li[contains(@class,'product-item')]//a[contains(@class,'product-item-link')]")));
	    	List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	    	        By.xpath("//li[contains(@class,'product-item')]//a[contains(@class,'product-item-link')]")));


	        if (products.isEmpty()) {
	            System.out.println("Error: No products found!");
	            return;
	        }

	        Random random = new Random();
	        WebElement randomProduct = products.get(random.nextInt(products.size()));

	        System.out.println("Selecting product: " + randomProduct.getText());
	        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", randomProduct);
	       // randomProduct.click();
	        
	        
	        // Scrolls product
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'})", randomProduct);
	        Thread.sleep(1000);
	        
	        // Click using JS if standard click fails

	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", randomProduct);



	        selectRandomSize();
	        selectRandomColor();
	        addToCart();

	        driver.navigate().to("https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html"); 
	    }

	    public void selectRandomSize() {
	        try {
	            List<WebElement> sizes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class, 'swatch-option text')]")));

	            if (!sizes.isEmpty()) {
	                WebElement randomSize = sizes.get(new Random().nextInt(sizes.size()));
	                randomSize.click();
	                System.out.println("Selected Size: " + randomSize.getText());
	            } else {
	                System.out.println("No size options available.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error: Unable to select size.");
	        }
	    }

	    public void selectRandomColor() {
	        try {
	            List<WebElement> colors = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class, 'swatch-option color')]")));

	            if (!colors.isEmpty()) {
	                WebElement randomColor = colors.get(new Random().nextInt(colors.size()));
	                randomColor.click();
	                System.out.println("Selected Color: " + randomColor.getAttribute("aria-label"));
	            } else {
	                System.out.println("No color options available.");
	            }
	        } catch (Exception e) {
	            System.out.println("Error: Unable to select color.");
	        }
	    }

	    public void addToCart() {
	        try {
	            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
	            addToCartButton.click();
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'message-success')]")));
	            System.out.println("Product added to cart!");
	        } catch (TimeoutException e) {
	            System.out.println("Error: Unable to add product to cart");
	        }
	    }

	    public void openCart() {
	        try {
	            WebElement myCart = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[@class='action showcart']")));
	            myCart.click();
	            
	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[contains(@class, 'block-minicart')]"))); 

	            System.out.println("Cart opened successfully.");
	        } catch (TimeoutException e) {
	            System.out.println("Error: Unable to open the cart.");
	        }
	    }

	    public void viewAndEditCart() {
	        try {
	            WebElement viewEditCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.linkText("View and Edit Cart")));
	            viewEditCartBtn.click();

	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[@class='cart-container']")));

	            System.out.println("Viewing and editing the cart.");
	        } catch (TimeoutException e) {
	            System.out.println("Error: Unable to view and edit cart.");
	        }
	    }

	    public void removeOneProductFromCart() throws InterruptedException {
	        try {
	        	Thread.sleep(2000);
	        	List<WebElement> removeButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                    By.xpath("//a[@class='action action-delete']")));

	            if (!removeButtons.isEmpty()) {
	                WebElement removeButton = removeButtons.get(0);
	                removeButton.click();

	                // we wait for cart to update
	                wait.until(ExpectedConditions.invisibilityOf(removeButton));
	                
	                System.out.println("Removed one product from the cart.");
	                
	            } else {
	                System.out.println("No products found in the cart to remove.");
	            }
	        } catch (TimeoutException e) {
	            System.out.println("Error: Unable to remove product from cart.");
	        }
	    }

	    public void checkoutButton() {
	        try {
	        	//wait for sometime
	            Thread.sleep(2000);

	            WebElement checkoutBtn = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));

	            // Scroll to button
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

	            // Wait and click
	            wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

	            System.out.println("Clicked 'Proceed to Checkout'");
	        } catch (Exception e) {
	            System.out.println("Error: Cannot click 'Proceed to Checkout'");
	        }
	    }
	    


}
