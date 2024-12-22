package com.bddframework.step_definition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	static WebDriver driver;
	static WebDriverWait wait;

	@BeforeClass
    public static void setUpClass() {
        System.out.println("BeforeClass: Setting up WebDriver");
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver-win32\\chromedriver.exe");
    }

	
	@Before
	public static void setUp()   {
		System.out.println("BeforeClass: Setting up WebDriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.linkText("My Account")).click();
		//Thread.sleep(3000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
	
	}

		
	@Given("User navigates to Login Page")
	public void user_navigates_to_login_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
		driver.findElement(By.linkText("Login")).click();
		
	}

	@And("User enters valid email {string}")
	@And("User enters invalid email {string}")
	public void user_enters_email(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
		driver.findElement(By.id("input-email")).sendKeys(email);
	}

	@And("User enters valid password {string}")
	@And("User enters invalid password {string}")
	public void user_enters_password(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-password")));
		driver.findElement(By.id("input-password")).sendKeys(password);
	}

	@When("User clicks on the login button")
	public void user_clicks_on_the_login_button() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Login']")));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}

	@Then("User is directed to AccountPage")
	public void user_is_directed_to_account_page() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Account")));
		String accountPageTitle = driver.getTitle();
		assertEquals("My Account", accountPageTitle);
		driver.quit();
	}

	@Then("User gets warning message about the unaccepted credentials")
	public void user_gets_warning_message_about_the_unaccepted_credentials() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-danger")));
		String warningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger")).getText();
		assertTrue(warningMessage.contains("Warning: No match for E-Mail Address and/or Password."));
		driver.quit();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
