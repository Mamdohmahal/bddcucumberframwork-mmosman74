@TutorialsNinjaLogin
Feature: Register functionality of TutorialsNinja
  
@MandatoryFields
Scenario: Register with mandatory fields
Given User navigates to RegisterPage
And User enters the below mandatory fields
  |Firstname   | Lastname  | telephone  |  Password | Confirmpassword |
  |Mamdouh     | Osman     | 123456789  |  Abc@123  | Abc@123         |
And User selects Privacy policy Checkbox
When User Clicks on Continue button
Then User is directed to AccountSuccessPage

@DuplicateEmail
Scenario: Register with existing email
Given User navigates to RegisterPage
And User enters the below mandatory fields with duplicate email
  |Firstname   | Lastname  | email                            | telephone  |  Password | Confirmpassword |
  |Mamdouh     | Osman     | bootcampmamdouh@gmail.com        | 123456789  |  Abc@123  | Abc@123         |
And User selects Privacy policy Checkbox
When User Clicks on Continue button
Then User gerts warning message of duplicate email


@IncorrectConfirmPassword
Scenario: Enteres different valuses for passwords
Given User navigates to RegisterPage
And User enters the below fields with not matching pwds
	|Firstname   | Lastname  | telephone  |  Password | Confirmpassword |
  |Mamdouh     | Osman     | 123456789  |  Abc@123  | Abc@1234        |
And User selects Privacy policy Checkbox
When User Clicks on Continue button
Then User gerts warning message of incorrect confirm passwords
