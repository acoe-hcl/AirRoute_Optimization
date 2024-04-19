import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GuruAutomationTest {

    public static void main(String[] args) {
        // Setting up WebDriver and opening the specified URL
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://52.214.46.0/");

        // Step 1: User enters username and password
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("priya");
        passwordField.sendKeys("Hcltech@123");

        // Step 2: User clicks on "Submit" button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Step 3: User lands on Card Management Application home page
        // Assuming the home page elements can be identified by their respective locators

        // Step 4: User navigates to the "ACCOUNT VIEW" page
        // Assuming there is a link or button on the home page to navigate to the account view page

        // Step 5: User enters a valid account ID and clicks on "Search" button
        WebElement accountIDField = driver.findElement(By.id("accountID"));
        WebElement searchButton = driver.findElement(By.id("search"));
        accountIDField.sendKeys("2");
        searchButton.click();

        // Step 6: System retrieves account information
        // Assuming there is a validation to check if the account information is displayed correctly

        // Additional Scenario: User enters invalid account ID (blank)
        accountIDField.clear();
        searchButton.click();
        WebElement errorMessage = driver.findElement(By.id("error"));
        String expectedErrorMessage = "Account Filter must be a non-zero 11 digit number";
        if (errorMessage.getText().equals(expectedErrorMessage)) {
            System.out.println("Test validation passed: Error message displayed correctly");
        } else {
            System.out.println("Test validation failed: Error message is not displayed correctly");
        }

        // Additional Scenario: User enters invalid account ID (not numeric)
        accountIDField.sendKeys("@");
        searchButton.click();
        if (errorMessage.getText().equals(expectedErrorMessage)) {
            System.out.println("Test validation passed: Error message displayed correctly");
        } else {
            System.out.println("Test validation failed: Error message is not displayed correctly");
        }

        // Additional Scenario: User enters invalid account ID (less than 11 digits)
        accountIDField.clear();
        accountIDField.sendKeys("1234567890");
        searchButton.click();
        if (errorMessage.getText().equals(expectedErrorMessage)) {
            System.out.println("Test validation passed: Error message displayed correctly");
        } else {
            System.out.println("Test validation failed: Error message is not displayed correctly");
        }

        // Additional Scenario: User enters invalid account ID (more than 11 digits)
        accountIDField.clear();
        accountIDField.sendKeys("123456789012");
        searchButton.click();
        if (errorMessage.getText().equals(expectedErrorMessage)) {
            System.out.println("Test validation passed: Error message displayed correctly");
        } else {
            System.out.println("Test validation failed: Error message is not displayed correctly");
        }

        // Closing the WebDriver
        driver.quit();
    }

}