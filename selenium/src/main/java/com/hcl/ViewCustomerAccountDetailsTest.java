import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ViewCustomerAccountDetailsTest {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the Card Management Screen
        driver.get("http://52.214.46.0/");

        // Enter username and password
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        username.sendKeys("priya");
        password.sendKeys("Hcltech@123");

        // Click on the "Submit" button
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();

        // Verify landing on Card Management Application home page
        String expectedHomePageTitle = "Card Management Application Home";
        String actualHomePageTitle = driver.getTitle();
        if (actualHomePageTitle.equals(expectedHomePageTitle)) {
            System.out.println("Successfully landed on Card Management Application home page");
        } else {
            System.out.println("Failed to land on Card Management Application home page");
        }

        // Navigate to the "ACCOUNT VIEW" page
        WebElement accountViewLink = driver.findElement(By.linkText("ACCOUNT VIEW"));
        accountViewLink.click();

        // Enter a valid account ID and click on the "Search" button
        WebElement accountIdInput = driver.findElement(By.name("accountId"));
        WebElement searchButton = driver.findElement(By.xpath("//input[@value='Search']"));
        accountIdInput.sendKeys("2");
        searchButton.click();

        // Verify retrieving account information
        WebElement accountInfo = driver.findElement(By.id("accountInfo"));
        if (accountInfo.isDisplayed()) {
            System.out.println("Account information retrieved successfully");
        } else {
            System.out.println("Failed to retrieve account information");
        }

        // Close the browser
        driver.quit();
    }
}