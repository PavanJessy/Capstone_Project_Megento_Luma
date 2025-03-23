package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import objects.HomePageObjects;

import java.time.Duration;

public class HomePage extends BaseClass {

	static HomePageObjects obj;

	//Clicks on the signin link on the home page
	public static void clickOnSignIn() {
		try {
			obj = new HomePageObjects();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(obj.signinLink));
			obj.signinLink.click();
			System.out.println("Clicked on Sign In link");
		} catch (Exception e) {
			System.out.println("Exception in clickOnSignIn method: " + e);
		}
	}

	//Click on the create new Account
	public static void clickOnCreateNewAccount() {
		try {
			obj = new HomePageObjects();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(obj.createNewAccountLink));
			obj.createNewAccountLink.click();
			System.out.println("Clicked on Create New Account link");
		} catch (Exception e) {
			System.out.println("Exception in clickOnCreateNewAccount method: " + e);
		}
	}

	// Retrives and returns the home page
	public static String getHomePageText() {
		try {
			obj = new HomePageObjects();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(obj.homepageText));
			String text = obj.homepageText.getText();
			System.out.println("Homepage text: " + text);
			return text;
		} catch (Exception e) {
			System.out.println("Exception in getHomePageText method: " + e);
			return null;
		}
	}

}
