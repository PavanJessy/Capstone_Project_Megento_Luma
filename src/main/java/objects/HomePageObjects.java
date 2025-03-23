package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class HomePageObjects extends BaseClass {
	
     // Constructor:Initializes pagefactory elements
	public HomePageObjects() {
		PageFactory.initElements(driver, this);
	}

	public By signIn = By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]");
	public By createNewAccount = By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']");
	public By homepage = By.xpath("//span[@class='base']");
	
    // Sign In Link
	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
	public WebElement signinLink;
	// Create new Account Link
	@FindBy(xpath = "/html/body/div[2]/header/div[1]/div/ul/li[3]/a")
	public WebElement createNewAccountLink;
	// Homepage Text
	@FindBy(xpath = "//span[@class='base']")
	public WebElement homepageText;

}
