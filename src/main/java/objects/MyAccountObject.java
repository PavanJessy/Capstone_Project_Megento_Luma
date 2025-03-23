package objects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
public class MyAccountObject extends BaseClass {

    public MyAccountObject() {
        PageFactory.initElements(driver, this);
    }

    // ✅ Fixed XPath for searchItem
    public By myAccountText = By.xpath("//span[@class='base']");
    public By searchBox = By.xpath("//input[@id='search']");
    public By searchItem = By.xpath("//ul[@role='listbox']/li/span[1]"); // ✅ Fixed XPath
    public By RegistrationText = By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");

    @FindBy(xpath = "//span[@class='base']")
    public WebElement myAccountTextElement;

    @FindBy(xpath = "//input[@id='search']")
    public WebElement searchBoxElement;

    // ✅ `searchList` will now be correctly populated
    @FindBy(xpath = "//ul[@role='listbox']/li/span[1]")
    public List<WebElement> searchList;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    public WebElement RegistrationTextElement;
}