Feature: Checking User Registration  
@Signup
  Scenario Outline: To check the Creating New Customer Account page with valid and invalid data  
    Given User opens the browser  
    And User navigates to the application URL "https://magento.softwaretestingboard.com/"  
    When User clicks the link Create an account  
    And User enters the valid '<FirstName>' in firstname textbox  
    And User enters the valid '<LastName>' in lastname textbox  
    And User enters the invalid '<InvalidEmail>' in email textbox  
    And User enters the valid '<Password>' in password textbox  
    And User enters the valid '<ConfirmPassword>' in confirm password textbox  
    And User clicks the Create an account button  
    Then User verifies the '<error>' message displayed under the email textbox  
    When User re-enters the email with valid data '<ValidEmail>'  
    And User clicks the Create an account button  
    #Then User verifies thank you '<Message>' in the next page  

    Examples:  
      | FirstName | LastName | InvalidEmail          | Password    | ConfirmPassword | ValidEmail               | Message                 												    | error																												| 
      | Anantha    | Pavan    | 	qkjbqke			         | Pass@1234   | Pass@1234	     | pavan.anantha@gmail.com    |Thank you for registering with Main Website Store.   | Please enter a valid email address (Ex: johndoe@domain.com).| 
    