import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GuruTestAutomationScript {

    public static void main(String[] args) {
        // Set Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Launch the Card Management Screen
        driver.get("http://52.214.46.0/");

        // Verify if the Card Management Screen is loaded successfully
        if (driver.getTitle().equals("Card Management Screen")) {
            System.out.println("Card Management Screen is loaded successfully");
        } else {
            System.out.println("Failed to load Card Management Screen");
            driver.quit();
            return;
        }

        // Enter username and password
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.sendKeys("priya");
        passwordField.sendKeys("Hcltech@123");
        submitButton.click();

        // Verify if the user is on the Card Management Application home page
        if (driver.getTitle().equals("Card Management Application")) {
            System.out.println("User is on the Card Management Application home page");
        } else {
            System.out.println("Failed to navigate to the Card Management Application home page");
            driver.quit();
            return;
        }

        // Navigate to the "ACCOUNT VIEW" page
        WebElement accountViewLink = driver.findElement(By.linkText("ACCOUNT VIEW"));
        accountViewLink.click();

        // Wait for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enter a valid account ID and click on "Search" button
        WebElement accountIdField = driver.findElement(By.id("accountId"));
        WebElement searchButton = driver.findElement(By.id("search"));

        accountIdField.sendKeys("2");
        searchButton.click();

        // Verify if the system retrieves account information
        WebElement accountInfo = driver.findElement(By.className("account-info"));

        if (accountInfo.isDisplayed()) {
            System.out.println("Account information is retrieved successfully");
        } else {
            System.out.println("Failed to retrieve account information");
            driver.quit();
            return;
        }

        // Close the browser
        driver.quit();
    }
}