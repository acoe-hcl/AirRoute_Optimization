import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ViewCustomerAccountDetailsTest {
    public static void main(String[] args) {

        // Initialize WebDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Feature: View Customer Account Details

            // Scenario: View Account Details
            driver.get("http://localhost/");
            Thread.sleep(1500);

            // Enter username and password
            WebElement usernameInput = driver.findElement(By.id("username"));
            usernameInput.sendKeys("testuser");
            WebElement passwordInput = driver.findElement(By.id("password"));
            passwordInput.sendKeys("testpassword");

            // Click on "Submit" button
            WebElement submitButton = driver.findElement(By.id("submitBtn"));
            submitButton.click();
            Thread.sleep(1500);

            // Assert home page loaded; e.g. by presence of home identifier
            WebElement homeHeader = driver.findElement(By.id("homeHeader"));
            if (!homeHeader.isDisplayed()) {
                throw new AssertionError("Home page did not load.");
            }

            // Navigate to ACCOUNT VIEW page
            WebElement accountViewMenu = driver.findElement(By.id("accountViewMenu"));
            accountViewMenu.click();
            Thread.sleep(1000);

            // Enter valid account ID "2" and click "Search"
            WebElement accountIdInput = driver.findElement(By.id("accountId"));
            accountIdInput.clear();
            accountIdInput.sendKeys("2");
            WebElement searchBtn = driver.findElement(By.id("searchBtn"));
            searchBtn.click();
            Thread.sleep(2000);

            // Verify system retrieves account information (e.g. presence of account summary)
            WebElement accountInfo = driver.findElement(By.id("accountInfo"));
            if (!accountInfo.isDisplayed()) {
                throw new AssertionError("Account information not retrieved.");
            }

            // ----------------------------------------------------------------------
            // Scenario: User enters invalid account ID (blank)
            driver.get("http://localhost/");
            Thread.sleep(1500);

            accountViewMenu = driver.findElement(By.id("accountViewMenu"));
            accountViewMenu.click();
            Thread.sleep(1000);

            accountIdInput = driver.findElement(By.id("accountId"));
            accountIdInput.clear();
            searchBtn = driver.findElement(By.id("searchBtn"));
            searchBtn.click();
            Thread.sleep(1000);

            WebElement errorMsg1 = driver.findElement(By.id("accountIdError"));
            String expectedErrorMsg1 = "Account Filter must be a non-zero 11 digit number";
            if (!errorMsg1.getText().equals(expectedErrorMsg1)) {
                throw new AssertionError("Error message mismatch for blank account ID.");
            }

            WebElement errorPrompt1 = driver.findElement(By.id("errorPrompt"));
            if (!errorPrompt1.isDisplayed()) {
                throw new AssertionError("Prompt for valid account ID not displayed (blank).");
            }

            // ----------------------------------------------------------------------
            // Scenario: User enters invalid account ID (not numeric)
            driver.get("http://localhost/");
            Thread.sleep(1500);

            accountViewMenu = driver.findElement(By.id("accountViewMenu"));
            accountViewMenu.click();
            Thread.sleep(1000);

            accountIdInput = driver.findElement(By.id("accountId"));
            accountIdInput.clear();
            accountIdInput.sendKeys("@");
            searchBtn = driver.findElement(By.id("searchBtn"));
            searchBtn.click();
            Thread.sleep(1000);

            WebElement errorMsg2 = driver.findElement(By.id("accountIdError"));
            String expectedErrorMsg2 = "Account Filter must be a non-zero 11 digit number";
            if (!errorMsg2.getText().equals(expectedErrorMsg2)) {
                throw new AssertionError("Error message mismatch for non-numeric account ID.");
            }

            WebElement errorPrompt2 = driver.findElement(By.id("errorPrompt"));
            if (!errorPrompt2.isDisplayed()) {
                throw new AssertionError("Prompt for valid account ID not displayed (non-numeric).");
            }

            // ----------------------------------------------------------------------
            // Scenario: User enters invalid account ID (less than 11 digits)
            driver.get("http://localhost/");
            Thread.sleep(1500);

            accountViewMenu = driver.findElement(By.id("accountViewMenu"));
            accountViewMenu.click();
            Thread.sleep(1000);

            accountIdInput = driver.findElement(By.id("accountId"));
            accountIdInput.clear();
            accountIdInput.sendKeys("1234567890");
            searchBtn = driver.findElement(By.id("searchBtn"));
            searchBtn.click();
            Thread.sleep(1000);

            WebElement errorMsg3 = driver.findElement(By.id("accountIdError"));
            String expectedErrorMsg3 = "Account Filter must be a non-zero 11 digit number";
            if (!errorMsg3.getText().equals(expectedErrorMsg3)) {
                throw new AssertionError("Error message mismatch for account ID less than 11 digits.");
            }

            // ----------------------------------------------------------------------
            // Scenario: User enters invalid account ID (more than 11 digits)
            driver.get("http://localhost/");
            Thread.sleep(1500);

            accountViewMenu = driver.findElement(By.id("accountViewMenu"));
            accountViewMenu.click();
            Thread.sleep(1000);

            accountIdInput = driver.findElement(By.id("accountId"));
            accountIdInput.clear();
            accountIdInput.sendKeys("123456789012");
            searchBtn = driver.findElement(By.id("searchBtn"));
            searchBtn.click();
            Thread.sleep(1000);

            WebElement errorMsg4 = driver.findElement(By.id("accountIdError"));
            String expectedErrorMsg4 = "Account Filter must be a non-zero 11 digit number";
            if (!errorMsg4.getText().equals(expectedErrorMsg4)) {
                throw new AssertionError("Error message mismatch for account ID more than 11 digits.");
            }

            System.out.println("All test scenarios passed.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
