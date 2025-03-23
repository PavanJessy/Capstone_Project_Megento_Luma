package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class ProductSearchAddToCartObjects extends BaseClass {
	public ProductSearchAddToCartObjects() {
	PageFactory.initElements(driver, this);
}
public By products = By.xpath("//div[@class='product details product-item-details' ]/strong/a");
public By next = By.xpath("//body[1]/div[2]/main[1]/div[3]/div[1]/div[2]/div[3]/div[2]/ul[1]/li[4]/a[1]");



@FindBy(xpath = "//div[@class='product details product-item-details' ]/strong/a")
public List<WebElement> productsItem;
@FindBy(xpath = "//body[1]/div[2]/main[1]/div[3]/div[1]/div[2]/div[3]/div[2]/ul[1]/li[4]/a[1]")
public WebElement nextElemnet;



}
