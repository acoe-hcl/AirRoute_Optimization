import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {

    public static void main(String[] args) {
        // Set the path of the ChromeDriver executable file
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");

        // Enter username
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("priya");

        // Enter password
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Hcltech@123");

        // Click on the Submit button
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Verify landing on Card Management Application home page
        WebElement homePageHeader = driver.findElement(By.cssSelector("h1"));
        if (homePageHeader.getText().equals("Card Management Application")) {
            System.out.println("Successfully landed on Card Management Application home page");
        } else {
            System.out.println("Failed to land on Card Management Application home page");
        }

        // Navigate to ACCOUNT VIEW page
        WebElement accountViewLink = driver.findElement(By.linkText("ACCOUNT VIEW"));
        accountViewLink.click();

        // Enter a valid account ID and click on Search button
        WebElement accountIdInput = driver.findElement(By.name("accountId"));
        accountIdInput.sendKeys("2");
        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButton.click();

        // Verify the system retrieves account information
        WebElement accountInfo = driver.findElement(By.cssSelector(".account-info"));
        if (accountInfo.isDisplayed()) {
            System.out.println("Account information retrieved successfully");
        } else {
            System.out.println("Failed to retrieve account information");
        }

        // Close the browser
        driver.quit();
    }
}