package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BaseClass;
import objects.RegistrationPageObjects;

public class RegistrationPage extends BaseClass {

	static RegistrationPageObjects obj;
 
	// Enter first name in the firstName field//Custum methods
	public static void enterFirstName(String firstName) {
		
		try {
			obj = new RegistrationPageObjects();
			wait.until(ExpectedConditions.visibilityOf(obj.firstNameElement));
			obj.firstNameElement.sendKeys(firstName);
			System.out.println("Enter the first name : " + firstName);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in the EnterFirstName: " + e);
		}
		
	}

	// Enter Last name in the LastName field
	public static void enterLastName(String lastName) {
		try {
			obj = new RegistrationPageObjects();
			wait.until(ExpectedConditions.visibilityOf(obj.lastNameElement));
			obj.lastNameElement.sendKeys(lastName);
			System.out.println("Entered Last Name: " + lastName);
		} catch (Exception e) {
			System.out.println("Exception in enterLastName method: " + e);
		}
	}

	// Enter the mail in the Email field
	public static void enterEmail(String str) {
		try {
			obj = new RegistrationPageObjects();
			wait.until(ExpectedConditions.visibilityOf(obj.emailElement));
			obj.emailElement.clear();
			obj.emailElement.sendKeys(str);
			System.out.println("Entered Email: " + str);
		} catch (Exception e) {
			System.out.println("Exception in enterEmail method: " + e);
		}
	}

	//Enter the password in the password field
	public static void enterPassword(String password) {
		try {
			obj = new RegistrationPageObjects();
			wait.until(ExpectedConditions.visibilityOf(obj.passwordElement));
			obj.passwordElement.sendKeys(password);
			System.out.println("Entered Password");
		} catch (Exception e) {
			System.out.println("Exception in enterPassword method: " + e);
		}
	}

	//Enter the Confirm pass in the confirm password field
	public static void enterConfirmPassword(String confirmPassword) {
		try {
			obj = new RegistrationPageObjects();
			wait.until(ExpectedConditions.visibilityOf(obj.confirmPasswordElement));
			obj.confirmPasswordElement.sendKeys(confirmPassword);
			System.out.println("Entered Confirm Password");
		} catch (Exception e) {
			System.out.println("Exception in enterConfirmPassword method: " + e);
		}
	}

	// Clicks the Create an Account button
	public static void clickCreateAnAccount() {
		try {
			obj = new RegistrationPageObjects();
			wait.until(ExpectedConditions.elementToBeClickable(obj.createAnAccountElement));
			obj.createAnAccountElement.click();
			System.out.println("Clicked Create an Account");
		} catch (Exception e) {
			System.out.println("Exception in clickCreateAnAccount method: " + e);
		}
	}

	// displays error message in email field
	public static String emailErrorMessage() {
		try {
			obj = new RegistrationPageObjects();
			wait.until(ExpectedConditions.visibilityOf(obj.emailErrorElement));
			String message = obj.emailErrorElement.getText();
			System.out.println("Email Error Message: " + message);
			return message;
		} catch (Exception e) {
			System.out.println("Exception in getEmailErrorMessage method: " + e);
			return null;
		}
	}
}
