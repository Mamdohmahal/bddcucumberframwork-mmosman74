@TutorialsNinjaLogin
Feature: The functionality of Search for product in TutorialsNinja
  
@ValidProduct
Scenario: Search for a valid product
Given User navigates to Home Page
And User enters valid product in the search field "HP LP3065"
When User clicks on the Search button
Then User is directed to the ProductPage

@InvalidProduct
Scenario: Search for an invalid product
Given User navigates to Home Page
And User enters invalid product in the search field "Dell"
When User clicks on the Search button
Then User is redirected to a search page 
And User gets a message informing that no product matching search criteria

@NoProduct
Scenario: Click on Search button without entering search words
Given User navigates to Home Page
When User clicks on the Search button
Then User is redirected to a search page 
And User gets a message informing that no product matching search criteria
