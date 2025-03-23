Feature: Checking Login, Product Search, and Adding Product to Cart 
 
@AddToCart
  Scenario Outline: To check the Sign In functionality, auto-suggestion feature in search, and addition of product to the cart  
    Given User opens the browser  
    And User navigates to the application URL "https://magento.softwaretestingboard.com/"  
    When User clicks the "Sign In" link on the homepage  
    And User enters the valid Login Credentials 
    |ExcelData.xlsx|
    And User clicks the "Sign In" button  
    And User enters the data "jacket" in the search textbox  
    And User selects jackets for men from the auto-suggestion list  '<options>'
    And User clicks on the '<jacket>'  from the search results
    And User selects size, color, and quantity from 
    |ExcelData.xlsx|  
    And User clicks the Add to Cart button  
    Then Verify whether the product is added to cart with message '<Message>'  

    Examples:  
      | options            | jacket               								  	 | Message                             																   |  
      |Jacket for men      | Typhon Performance Fleece-lined Jacket    | You added Typhon Performance Fleece-lined Jacket to your shopping cart. |