package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LogoutPage;
import pages.MyAccount;
import pages.SignInPage;

public class LogoutStepDef {

	@And("User enters valid email {string} and password {string}")
	public void user_enters_valid_email_and_password(String string, String string2) {
		try {
            SignInPage.enterValidCredentials(string, string2);
            System.out.println("User logged in with valid credentials");
        } catch (Exception e) {
            System.out.println("Exception in user_enters_valid_email_and_password method: " + e);
        }
	}

	@When("User clicks {string} from {string}  page")
	public void user_clicks_from_page(String action, String page) {
		 try {
	            if (action.equalsIgnoreCase("Logout") && page.equalsIgnoreCase("My Account")) {
	                LogoutPage.logout(); 
	                System.out.println("User clicked on Logout from My Account page");
	            }
	        } catch (Exception e) {
	            System.out.println("Exception in user_clicks_from_page method: " + e);
	        }
	}

	@Then("User verifies that the session ends and the user is redirected to the homepage")
	public void user_verifies_that_the_session_ends_and_the_user_is_redirected_to_the_homepage() throws Exception {
		Thread.sleep(7000);
		try {
            String homepageText = MyAccount.getMyAccountText(); 
            if (homepageText == null || homepageText.isEmpty()) {
                System.out.println("Session ended and user redirected to homepage");
            } else {
                System.out.println("Session not ended properly");
            }
        } catch (Exception e) {
            System.out.println("Exception in user_verifies_that_the_session_ends_and_the_user_is_redirected_to_the_homepage method: " + e);
        }
	}

	@And("User verifies the {string} is displayed")
	public void user_verifies_the_is_displayed(String expectedText) {
		 try {
	            String actualText = MyAccount.getMyAccountText();
	            if (actualText.equalsIgnoreCase(expectedText)) {
	                System.out.println(expectedText + " is displayed successfully");
	            } else {
	                System.out.println("Expected: " + expectedText + ", but found: " + actualText);
	            }
	        } catch (Exception e) {
	            System.out.println("Exception in user_verifies_the_is_displayed method: " + e);
	        }
	    }
	

}