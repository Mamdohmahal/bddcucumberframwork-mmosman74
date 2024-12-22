@TutorialsNinjaLogin
Feature: Login functionality of TutorialsNinja
  
@ValidCredentials
Scenario: Login with valid credentials
Given User navigates to Login Page
And User enters valid email "bootcampmamdouh@gmail.com"
And User enters valid password "Bcmamdouh123"
When User clicks on the login button
Then User is directed to AccountPage


@InvalidCredentials
Scenario: Login with invalid credentials
Given User navigates to Login Page
And User enters invalid email "invbootcampmamdouh@gmail.com"
And User enters invalid password "Bcmamdouh123"
When User clicks on the login button
Then User gets warning message about the unaccepted credentials 

@ValidEmailInvalidPassword
Scenario: Login with valid email and invalid password
Given User navigates to Login Page
And User enters valid email "bootcampmamdouh@gmail.com"
And User enters invalid password "Bcmamdouh12"
When User clicks on the login button
Then User gets warning message about the unaccepted credentials

@InvalidEmailValidPassword
Scenario: Login with invalid email and valid password
Given User navigates to Login Page
And User enters valid email "invbootcampmamdouh@gmail.com"
And User enters invalid password "Bcmamdouh123"
When User clicks on the login button
Then User gets warning message about the unaccepted credentials 

@Nocredentials
Scenario: Login without email and password
Given User navigates to Login Page
When User clicks on the login button
Then User gets warning message about the unaccepted credentials 