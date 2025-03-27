package base;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	 public static WebDriver driver;
	    public static WebElement element;
	    public static WebDriverWait wait;
	    public static Actions act;
	    public static Select select;
	    public static JavascriptExecutor js;
	    
	    //Method to initialize and launch the browser
	    public static void openBrowser() {
	        try {
	            String choice = Utility.properties("browser"); 
	            if (choice.equalsIgnoreCase("Chrome")) {
	                driver = new ChromeDriver();
	            } else if (choice.equalsIgnoreCase("Edge")) {
	                driver = new EdgeDriver();
	            } else {
	                throw new IllegalArgumentException("Invalid browser type in properties file");
	            }
	            
	           //Explicit timeout of 20 seconds
	            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	            // Implicit wait of 10 sec for locating elements
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	            driver.manage().window().maximize();

	           
	            act = new Actions(driver);

	            System.out.println(choice + " Browser launched successfully");
	            
	        } catch (Exception e) {
	            System.out.println("Browser - openBrowser: " + e.getMessage());
	        }
	    }
	    
	    //Method to navigate to the URL
	    public static void navigateToUrl(String string) {
	        try {
	            String url = Utility.properties("url"); 
	            driver.get(url);
	            System.out.println("Navigated to: " + url);
	        } catch (Exception e) {
	            System.out.println("Browser - navigateToUrl: " + e.getMessage());
	        }
	    }
	    // Method to close browser
	    public static void closeBrowser() {
	        try {
	            if (driver != null) {
	                driver.quit();
	                driver = null; 
	                System.out.println("Browser closed successfully");
	            }
	        } catch (Exception e) {
	            System.out.println("Browser - closeBrowser: " + e.getMessage());
	        }
	    }

}
