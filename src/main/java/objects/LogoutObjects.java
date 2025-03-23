package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class LogoutObjects extends BaseClass {

    public LogoutObjects() {
        PageFactory.initElements(driver, this);
    }

    public By accountDropdown = By.xpath("//button[@data-action='customer-menu-toggle']");
    public By logout = By.xpath("//a[contains(text(),'Sign Out')]");


    @FindBy(xpath = "//button[@data-action='customer-menu-toggle']")
    public WebElement accountDropdownElement;

    @FindBy(xpath = "//a[contains(text(),'Sign Out')]")
    public WebElement logoutElement;

}