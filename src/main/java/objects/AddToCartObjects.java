package objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

// Constructor
public class AddToCartObjects extends BaseClass{
	public AddToCartObjects() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@attribute-code='size']/div/div")
    public List<WebElement> sizeOptions;

    @FindBy(xpath = "//div[contains(@class, 'swatch-option color')]")
    public List<WebElement> colorOptions;


    @FindBy(id = "qty")
    public WebElement quantityBox;


    @FindBy(id = "product-addtocart-button")
    public WebElement addToCartButton;

    @FindBy(css = ".message-success")
    public WebElement cartMessage;

}
