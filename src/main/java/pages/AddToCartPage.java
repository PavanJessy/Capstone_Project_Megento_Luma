package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import objects.AddToCartObjects;
public class AddToCartPage extends BaseClass {

    static AddToCartObjects obj;

    public static void selectSize(String size) {
        try {
            obj = new AddToCartObjects(); //Initialize object
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(obj.sizeOptions));
            
            //Loop for Size options to find a match
            for (WebElement sizeOption : obj.sizeOptions) {
                if (sizeOption.getText().equalsIgnoreCase(size)) {
                    sizeOption.click();
                    System.out.println("Selected size: " + size);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in selectSize: " + e);
        }
    }

    public static void selectColor(String color) {
        try {
            obj = new AddToCartObjects();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(obj.colorOptions));
            
            //Loop for colour options to fina a match
            for (WebElement colorOption : obj.colorOptions) {
                if (colorOption.getDomAttribute("option-label").equalsIgnoreCase(color)) {
                    colorOption.click();
                    System.out.println("Selected color: " + color);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in selectColor: " + e);
        }
    }

    // ✅ Enter quantity
    public static void enterQuantity(String quantity) {
        try {
            obj = new AddToCartObjects();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(obj.quantityBox));
            
            // clear and enter the quantity
            obj.quantityBox.clear();
            obj.quantityBox.sendKeys(quantity);
            System.out.println("Entered quantity: " + quantity);
        } catch (Exception e) {
            System.out.println("Exception in enterQuantity: " + e);
        }
    }

    // clicks the Add to cart button
    public static void clickAddToCart() {
        try {
            obj = new AddToCartObjects();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(obj.addToCartButton));
            obj.addToCartButton.click();
            System.out.println("Clicked on Add to Cart button");
        } catch (Exception e) {
            System.out.println("Exception in clickAddToCart: " + e);
        }
    }

    public static String getCartMessage() {
        try {
            obj = new AddToCartObjects();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(obj.cartMessage));
            String message = obj.cartMessage.getText();
            System.out.println("Cart message: " + message);
            return message;
        } catch (Exception e) {
            System.out.println("Exception in getCartMessage: " + e);
            return null;
        }
    }
}