As an AI language model, I can provide you with a sample Selenium Java automation test script based on the given feature file. However, please note that executing the script might not be possible here as it requires setting up the necessary environment and dependencies. The following is a sample implementation of the automation test script in Java:

```java
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class UpdateCustomerAccountDetailsTest {

    public static void main(String[] args) {
        // Set the path to ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();

        // Navigate to the Card Management Screen
        driver.get("http://52.214.46.0/");

        // Enter username
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("priya");

        // Enter password
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Hcltech@123");

        // Click on the Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Wait for the Card Management Application home page to load
        // ...

        // Navigate to the ACCOUNT UPDATE page
        // ...

        // Enter a valid account ID
        WebElement accountIdInput = driver.findElement(By.id("accountId"));
        accountIdInput.sendKeys("2");

        // Click on the Search button
        WebElement searchButton = driver.findElement(By.id("search"));
        searchButton.click();

        // Wait for the account information to retrieve
        // ...

        // Enter Credit Limit
        WebElement creditLimitInput = driver.findElement(By.id("creditLimit"));
        creditLimitInput.clear();
        creditLimitInput.sendKeys("6150");

        // Click on the Save button
        WebElement saveButton = driver.findElement(By.id("save"));
        saveButton.click();

        // Wait for the account information to update in the database
        // ...

        // Verify the success message is displayed
        WebElement successMessage = driver.findElement(By.id("successMessage"));
        if(successMessage.isDisplayed() && successMessage.getText().equals("Changes committed to database")) {
            System.out.println("Test Passed: Success message is displayed");
        } else {
            System.out.println("Test Failed: Success message is not displayed");
        }

        // Close the browser
        driver.quit();
    }
}
```

Please ensure that you have the necessary Selenium WebDriver and ChromeDriver set up properly before running the script. Remember to replace `"path/to/chromedriver"` with the actual path to your ChromeDriver executable.