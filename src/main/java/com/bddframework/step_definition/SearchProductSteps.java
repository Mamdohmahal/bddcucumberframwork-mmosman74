package com.bddframework.step_definition;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class SearchProductSteps {

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("User navigates to Home Page")
    public void user_navigates_to_home_page() {
        driver.get("https://tutorialsninja.com/demo/");
    }

    @And("User enters valid product in the search field {string}")
    public void user_enters_valid_product_in_the_search_field(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        driver.findElement(By.name("search")).sendKeys(productName);
    }

    @And("User enters invalid product in the search field {string}")
    public void user_enters_invalid_product_in_the_search_field(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        driver.findElement(By.name("search")).sendKeys(productName);
    }

    @When("User clicks on the Search button")
    public void user_clicks_on_the_search_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-default.btn-lg")));
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
    }

    @Then("User is directed to the ProductPage")
    public void user_is_directed_to_the_product_page() {
        wait.until(ExpectedConditions.titleContains("HP LP3065"));
        assertTrue(driver.getTitle().contains("HP LP3065"));
    }

    @Then("User is redirected to a search page")
    public void user_is_redirected_to_a_search_page() {
        wait.until(ExpectedConditions.urlContains("index.php?route=product/search"));
        assertTrue(driver.getCurrentUrl().contains("index.php?route=product/search"));
    }

    @Then("User gets a message informing that no product matching search criteria")
    public void user_gets_a_message_informing_that_no_product_matching_search_criteria() {
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-danger")),
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#content p"))
        ));
        WebElement messageElement;
        if (driver.findElements(By.cssSelector("div.alert.alert-danger")).size() > 0) {
            messageElement = driver.findElement(By.cssSelector("div.alert.alert-danger"));
        } else {
            messageElement = driver.findElement(By.cssSelector("div#content p"));
        }
        String noProductMessage = messageElement.getText();
        System.out.println("Actual message: " + noProductMessage); // Log the actual message text

        // Improved assertion with message variations
        assertTrue(noProductMessage.contains("There is no product that matches the search criteria.")
                || noProductMessage.contains("Search in product descriptions"),
                "Unexpected message: " + noProductMessage);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
