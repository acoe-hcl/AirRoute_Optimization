import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAutomationScript {
    public static void main(String[] args) {
        // Set the path of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");

        // Enter username and password
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("priya");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Hcltech@123");

        // Click on the Submit button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Check if the user landed on the Card Management Application home page
        if (driver.getCurrentUrl().equals("http://52.214.46.0/home")) {
            System.out.println("User landed on the Card Management Application home page.");
        } else {
            System.out.println("User did not land on the Card Management Application home page.");
        }

        // Navigate to the ACCOUNT VIEW page
        WebElement accountViewLink = driver.findElement(By.linkText("ACCOUNT VIEW"));
        accountViewLink.click();

        // Enter a valid account ID and click on the Search button
        WebElement accountIDInput = driver.findElement(By.id("accountID"));
        accountIDInput.sendKeys("2");

        WebElement searchButton = driver.findElement(By.id("search"));
        searchButton.click();

        // Check if the system retrieves account information
        WebElement accountInformation = driver.findElement(By.id("accountInfo"));
        if (accountInformation.isDisplayed()) {
            System.out.println("System retrieved account information.");
        } else {
            System.out.println("System did not retrieve account information.");
        }

        // Close the browser
        driver.quit();
    }
}