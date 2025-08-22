import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewCustomerAccountDetailsTest {

    public static void main(String[] args) {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // Scenario 1: View Account Details
            driver.get("http://localhost/");
            // Assume login page has username and password fields with ids "username" and "password"
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPass");
            // Assume submit button has id "submitBtn"
            WebElement submitButton = driver.findElement(By.id("submitBtn"));
            submitButton.click();

            // Wait for home page after login
            wait.until(ExpectedConditions.urlContains("home"));
            // Assume "ACCOUNT VIEW" navigation exists via link text
            WebElement accountViewLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ACCOUNT VIEW")));
            accountViewLink.click();

            // Enter valid account ID "2" in the account ID field with id "accountId"
            WebElement accountIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountId")));
            accountIdField.clear();
            accountIdField.sendKeys("2");
            // Assume search button has id "searchBtn"
            WebElement searchButton = driver.findElement(By.id("searchBtn"));
            searchButton.click();

            // Wait for account info to be displayed, assume id "accountInfo"
            WebElement accountInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountInfo")));
            assert accountInfo.isDisplayed();

            // Scenario 2: Invalid account ID (blank)
            driver.get("http://localhost/");
            // Login steps (reuse)
            usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPass");
            submitButton = driver.findElement(By.id("submitBtn"));
            submitButton.click();

            // Navigate to ACCOUNT VIEW
            wait.until(ExpectedConditions.urlContains("home"));
            accountViewLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ACCOUNT VIEW")));
            accountViewLink.click();

            // Account ID field blank
            accountIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountId")));
            accountIdField.clear();
            searchButton = driver.findElement(By.id("searchBtn"));
            searchButton.click();

            WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("accountIdError"))
            );
            String expectedMsg = "Account Filter must be a non-zero 11 digit number";
            assert errorMsg.getText().contains(expectedMsg);

            // Scenario 3: Invalid account ID (not numeric)
            driver.get("http://localhost/");
            usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPass");
            submitButton = driver.findElement(By.id("submitBtn"));
            submitButton.click();

            wait.until(ExpectedConditions.urlContains("home"));
            accountViewLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ACCOUNT VIEW")));
            accountViewLink.click();

            accountIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountId")));
            accountIdField.clear();
            accountIdField.sendKeys("@");
            searchButton = driver.findElement(By.id("searchBtn"));
            searchButton.click();

            errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("accountIdError"))
            );
            assert errorMsg.getText().contains(expectedMsg);

            // Scenario 4: Invalid account ID (less than 11 digits)
            driver.get("http://localhost/");
            usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPass");
            submitButton = driver.findElement(By.id("submitBtn"));
            submitButton.click();

            wait.until(ExpectedConditions.urlContains("home"));
            accountViewLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ACCOUNT VIEW")));
            accountViewLink.click();

            accountIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountId")));
            accountIdField.clear();
            accountIdField.sendKeys("1234567890");
            searchButton = driver.findElement(By.id("searchBtn"));
            searchButton.click();

            errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("accountIdError"))
            );
            assert errorMsg.getText().contains(expectedMsg);

            // Scenario 5: Invalid account ID (more than 11 digits)
            driver.get("http://localhost/");
            usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            passwordField = driver.findElement(By.id("password"));
            usernameField.sendKeys("testUser");
            passwordField.sendKeys("testPass");
            submitButton = driver.findElement(By.id("submitBtn"));
            submitButton.click();

            wait.until(ExpectedConditions.urlContains("home"));
            accountViewLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ACCOUNT VIEW")));
            accountViewLink.click();

            accountIdField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountId")));
            accountIdField.clear();
            accountIdField.sendKeys("123456789012");
            searchButton = driver.findElement(By.id("searchBtn"));
            searchButton.click();

            errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("accountIdError"))
            );
            assert errorMsg.getText().contains(expectedMsg);

            System.out.println("All scenarios executed successfully.");

        } finally {
            driver.quit();
        }
    }
}
