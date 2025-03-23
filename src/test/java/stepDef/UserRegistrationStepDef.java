package stepDef;



import org.testng.Assert;

import base.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.MyAccount;
import pages.RegistrationPage;

public class UserRegistrationStepDef {
	@Given("User opens the browser")
	public void user_opens_the_browser() {
		BaseClass.openBrowser();
	}

	@And("User navigates to the application URL {string}")
	public void user_navigates_to_the_application_url(String string) {
		BaseClass.navigateToUrl(string);
	}

	@When("User clicks the link Create an account")
	public void user_clicks_the_link_create_an_account() {
			HomePage.clickOnCreateNewAccount();
	}

	@And("User enters the valid {string} in firstname textbox")
	public void user_enters_the_valid_in_firstname_textbox(String string) {
		RegistrationPage.enterFirstName(string);
	}

	@And("User enters the valid {string} in lastname textbox")
	public void user_enters_the_valid_in_lastname_textbox(String string) {
      RegistrationPage.enterLastName(string);
	}

	@And("User enters the invalid {string} in email textbox")
	public void user_enters_the_invalid_in_email_textbox(String string) {
		RegistrationPage.enterEmail(string);
	}

	@And("User enters the valid {string} in password textbox")
	public void user_enters_the_valid_in_password_textbox(String string) {
		RegistrationPage.enterPassword(string);
	}

	@And("User enters the valid {string} in confirm password textbox")
	public void user_enters_the_valid_in_confirm_password_textbox(String string) {
		RegistrationPage.enterConfirmPassword(string);
	}

	@And("User clicks the Create an account button")
	public void user_clicks_the_create_an_account_button() {
		RegistrationPage.clickCreateAnAccount();
	}

	@Then("User verifies the {string} message displayed under the email textbox")
	public void user_verifies_the_message_displayed_under_the_email_textbox(String string) {
	   try {
		Assert.assertEquals(RegistrationPage.emailErrorMessage(),string );
	} catch (Exception e) {
		// TODO: handle exception
	}
	}

	@When("User re-enters the email with valid data {string}")
	public void user_re_enters_the_email_with_valid_data(String string) {
		RegistrationPage.enterEmail(string);
	}

	@Then("User verifies thank you {string} in the next page")
	public void user_verifies_thank_you_in_the_next_page(String string) {
		try {
			Assert.assertEquals(MyAccount.getRegistrationText(), string);
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}

}
