package stepDef;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;

import excel.Excel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddToCartPage;
import pages.HomePage;
import pages.MyAccount;
import pages.ProductSearchAddToCartPage;
import pages.SignInPage;

public class ProductSearchAddToCartStepDef {

	@When("User clicks the {string} link on the homepage")
	public void user_clicks_the_link_on_the_homepage(String string) {
		HomePage.clickOnSignIn();
	}

	@And("User enters the valid Login Credentials")
	public void user_enters_the_valid_login_credentials(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> credentials = Excel
				.dataFromExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\ExcelData.xlsx", 0);
		dataTable = DataTable.create(credentials);
		SignInPage.enterValidCredentials(dataTable.cell(0, 0), dataTable.cell(0, 1));
	}

	@And("User enters the data {string} in the search textbox")
	public void user_enters_the_data_in_the_search_textbox(String string) {
		MyAccount.enterSearchQuery(string);
	}

	@And("User selects jackets for men from the auto-suggestion list  {string}")
	public void user_selects_jackets_for_men_from_the_auto_suggestion_list(String string) {
		MyAccount.selectSuggestion(string);
	}

	@And("User clicks on the {string}  from the search results")
	public void user_clicks_on_the_from_the_search_results(String string) throws TimeoutException  {
		ProductSearchAddToCartPage.selectingSearchData(string);
	}

	@And("User selects size, color, and quantity from")
	public void user_selects_size_color_and_quantity_from(io.cucumber.datatable.DataTable dataTable) {
		try {
	        List<List<String>> data = Excel
	                .dataFromExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\ExcelData.xlsx", 0);
	            
	            if (data.size() > 1) {
	                dataTable = DataTable.create(data);
	                AddToCartPage.selectSize(dataTable.cell(1, 0));
	                AddToCartPage.selectColor(dataTable.cell(1, 1));
	                AddToCartPage.enterQuantity(dataTable.cell(1, 2));
	            } else {
	                System.out.println("Insufficient data in Excel");
	            }
	        } catch (IndexOutOfBoundsException e) {
	            System.out.println("Index out of bounds: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("Exception: " + e.getMessage());
	        }
	}

	@And("User clicks the Add to Cart button")
	public void user_clicks_the_add_to_cart_button() {
		AddToCartPage.clickAddToCart();
	}

	@Then("Verify whether the product is added to cart with message {string}")
	public void verify_whether_the_product_is_added_to_cart_with_message(String string) {
		try {
			Assert.assertEquals(AddToCartPage.getCartMessage(), string);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}