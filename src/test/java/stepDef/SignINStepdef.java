package stepDef;

import java.util.List;

import excel.Excel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SignInPage;

public class SignINStepdef
{
	@When("User clicks the link {string}")
	public void user_clicks_the_link(String string) {
	   HomePage.clickOnSignIn();
	}

	@And("User enters invalid email {string} and password {string}")
	public void user_enters_invalid_email_and_password(String string, String string2) {
	   SignInPage.enterInvalidEmail(string);
	   SignInPage.enterInvalidPassword(string2);
	   
	}

	@And("User clicks the {string} button")
	public void user_clicks_the_button(String string) {
	   SignInPage.clickSignIn();
	   
	}

	@Then("User verifies the error message {string}")
	public void user_verifies_the_error_message(String string) {
		 String actualMessage = SignInPage.getErrorMessage();
	        if (actualMessage.equals(string)) {
	            System.out.println("Error message verified successfully");
	        } else {
	            System.out.println("Expected: " + string + ", but found: " + actualMessage);
	        }
	}

	@When("User enters valid credentials from")
	public void user_enters_valid_credentials_from(DataTable dataTable) {
		List<List<String>> credentials = Excel.dataFromExcel(
				System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\ExcelData.xlsx",
				0);
		dataTable = DataTable.create(credentials);
		SignInPage.enterValidCredentials(dataTable.cell(0, 0),dataTable.cell(0, 1));
		
	}


	@Then("User verifies that {string} is displayed after login")
	public void user_verifies_that_is_displayed_after_login(String string) {
		  String actualTitle = HomePage.getHomePageText();
	        if (actualTitle.equalsIgnoreCase(string)) {
	            System.out.println("Login successful and 'My Account' page is displayed");
	        } else {
	            System.out.println("Expected: " + string + ", but found: " + actualTitle);
	        }
	    }
	

}
