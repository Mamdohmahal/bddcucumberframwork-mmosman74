package com.bddframework.step_definition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import com.bddframework.utils.EmailDate;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps {

	public WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public static void setUpClass() {
		System.out.println("BeforeClass: Setting up WebDriver");
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver-win32\\chromedriver.exe");
	}

	@Before
	public void OpenApplication() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.linkText("My Account")).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Given("User navigates to RegisterPage")
	public void user_navigates_to_register_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Register")));
		driver.findElement(By.linkText("Register")).click();
	}

	@And("User enters the below mandatory fields")
	public void user_enters_mandatory_fields(io.cucumber.datatable.DataTable dataTable) {
		for (Map<String, String> data : dataTable.asMaps(String.class, String.class)) {
			System.out.println(EmailDate.emailWithDateTimeStamp());
			String firstname = data.get("Firstname");
			String lastname = data.get("Lastname");
			String telephone = data.get("telephone");
			String password = data.get("Password");
			String confirmpassword = data.get("Confirmpassword");

			if (firstname != null && !firstname.isEmpty()) {
				driver.findElement(By.id("input-firstname")).sendKeys(firstname);
			}
			if (lastname != null && !lastname.isEmpty()) {
				driver.findElement(By.id("input-lastname")).sendKeys(lastname);
			}

			driver.findElement(By.id("input-email")).sendKeys(EmailDate.emailWithDateTimeStamp());

			if (telephone != null && !telephone.isEmpty()) {
				driver.findElement(By.id("input-telephone")).sendKeys(telephone);
			}
			if (password != null && !password.isEmpty()) {
				driver.findElement(By.id("input-password")).sendKeys(password);
			}
			if (confirmpassword != null && !confirmpassword.isEmpty()) {
				driver.findElement(By.id("input-confirm")).sendKeys(confirmpassword);
			}
		}
	}

	@And("User enters the below mandatory fields with duplicate email")
	public void user_enters_mandatory_fields_with_duplicate_email(io.cucumber.datatable.DataTable dataTable) {
		for (Map<String, String> data : dataTable.asMaps(String.class, String.class)) {

			String firstname = data.get("Firstname");
			String lastname = data.get("Lastname");
			String email = data.get("email");
			String telephone = data.get("telephone");
			String password = data.get("Password");
			String confirmpassword = data.get("Confirmpassword");

			if (firstname != null && !firstname.isEmpty()) {
				driver.findElement(By.id("input-firstname")).sendKeys(firstname);
			}
			if (lastname != null && !lastname.isEmpty()) {
				driver.findElement(By.id("input-lastname")).sendKeys(lastname);
			}
			if (email != null && !email.isEmpty()) {
				driver.findElement(By.id("input-email")).sendKeys(email);
			}
			if (telephone != null && !telephone.isEmpty()) {
				driver.findElement(By.id("input-telephone")).sendKeys(telephone);
			}
			if (password != null && !password.isEmpty()) {
				driver.findElement(By.id("input-password")).sendKeys(password);
			}
			if (confirmpassword != null && !confirmpassword.isEmpty()) {
				driver.findElement(By.id("input-confirm")).sendKeys(confirmpassword);
			}
		}
	}

	@And("User enters the below fields with not matching pwds")
	public void user_enters_the_below_fields_with_not_matching_pwds(io.cucumber.datatable.DataTable dataTable) {
		for (Map<String, String> data : dataTable.asMaps(String.class, String.class)) {
			System.out.println(EmailDate.emailWithDateTimeStamp());
			String firstname = data.get("Firstname");
			String lastname = data.get("Lastname");
			String telephone = data.get("telephone");
			String password = data.get("Password");
			String confirmpassword = data.get("Confirmpassword");

			if (firstname != null && !firstname.isEmpty()) {
				driver.findElement(By.id("input-firstname")).sendKeys(firstname);
			}
			if (lastname != null && !lastname.isEmpty()) {
				driver.findElement(By.id("input-lastname")).sendKeys(lastname);
			}

			driver.findElement(By.id("input-email")).sendKeys(EmailDate.emailWithDateTimeStamp());

			if (telephone != null && !telephone.isEmpty()) {
				driver.findElement(By.id("input-telephone")).sendKeys(telephone);
			}
			if (password != null && !password.isEmpty()) {
				driver.findElement(By.id("input-password")).sendKeys(password);
			}
			if (confirmpassword != null && !confirmpassword.isEmpty()) {
				driver.findElement(By.id("input-confirm")).sendKeys(confirmpassword);
			}
		}
	}

	@And("User selects Privacy policy Checkbox")
	public void user_selects_privacy_policy_checkbox() {
		wait.until(ExpectedConditions.elementToBeClickable(By.name("agree")));
		driver.findElement(By.name("agree")).click();
	}

	@When("User Clicks on Continue button")
	public void user_clicks_on_continue_button() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Continue']")));
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}

	@Then("User is directed to AccountSuccessPage")
	public void user_is_directed_to_account_success_page() {
		wait.until(ExpectedConditions.titleContains("Your Account Has Been Created!"));
		assertEquals(driver.getTitle(), "Your Account Has Been Created!");
		driver.quit();
	}

	@And("User enters firstname {string}")
	public void user_enters_firstname(String firstname) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));
		driver.findElement(By.id("input-firstname")).sendKeys(firstname);
	}

	@And("User enters lastname {string}")
	public void user_enters_lastname(String lastname) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-lastname")));
		driver.findElement(By.id("input-lastname")).sendKeys(lastname);
	}

	@And("User enters telephone {string}")
	public void user_enters_telephone(String telephone) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-telephone")));
		driver.findElement(By.id("input-telephone")).sendKeys(telephone);
	}

	@And("User enters password {string}")
	public void user_enters_password(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));
		driver.findElement(By.id("input-password")).sendKeys(password);
	}

	@And("User enters confirmpassword {string}")
	public void user_enters_confirmpassword(String confirmpassword) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-confirm")));
		driver.findElement(By.id("input-confirm")).sendKeys(confirmpassword);
	}

	@And("User selects Yes for subscribe newsletter radiobutton")
	public void user_selects_yes_for_subscribe_newsletter_radiobutton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='newsletter'][@value='1']")));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	}

	@Then("User gerts warning message of duplicate email")
	public void user_gets_warning_message_of_duplicate_email() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-danger")));
		String warningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger")).getText();
		assertTrue(warningMessage.contains("Warning: E-Mail Address is already registered!"));
		driver.quit();
	}

	@Then("User gerts warning message of incorrect confirm passwords")
	public void user_gets_warning_message_of_incorrect_confirm_passwords() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-danger")));
			String warningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger")).getText();
			assertTrue(warningMessage.contains("Password confirmation does not match password!"));
		} catch (Exception e) {
			System.out.println("Timeout occurred. Element not found: " + e.getMessage());
			// Optionally, capture a screenshot or further debug information here
		} finally {
			driver.quit();
		}

	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
