package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class AmazonSteps {

    WebDriver driver;

    // Initialize WebDriver
    @Given("I open Amazon")
    public void i_open_amazon() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Search for the product by ID
    @When("I search for product ID {string}")
    public void i_search_for_product_by_id(String productId) {
        driver.get("https://www.amazon.in");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(productId);
        searchBox.submit();
    }

    // Wait for the search results to be visible
    @Then("I should see search results")
    public void i_should_see_search_results() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Increased timeout
        try {
            // Wait for visibility of the search results (e.g., product name or results container)
            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.a-size-medium")));
            System.out.println("Search results are displayed.");
            assert(result.isDisplayed());
        } catch (TimeoutException e) {
            System.out.println("Search results not found within the timeout period.");
            throw e;  // Fail the test with a clearer error
        }
    }

    // Close WebDriver
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
