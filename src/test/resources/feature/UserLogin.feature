Feature: Checking Login with Valid and Invalid Credentials  
@SignIn
  Scenario Outline: To check the Login functionality with valid and invalid credentials  
    Given User opens the browser  
    And User navigates to the application URL "https://magento.softwaretestingboard.com/"  
    When User clicks the link "Sign In"  
    And User enters invalid email '<InvalidEmail>' and password '<Password>'  
    And User clicks the "Sign In" button  
    Then User verifies the error message "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."  
    When User enters valid credentials from
    |ExcelData.xlsx| 
    And User clicks the "Sign In" button  
    Then User verifies that "Home Page" is displayed after login  

    Examples:  
      | InvalidEmail          | Password     |  
      | invalid@gmail.com     | Pass@1234    |  

