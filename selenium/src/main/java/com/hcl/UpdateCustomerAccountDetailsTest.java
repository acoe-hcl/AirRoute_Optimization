import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UpdateCustomerAccountDetailsTest {
    public static void main(String[] args) {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Open the Card Management Screen
        driver.get("[http://52.214.46.0/|http://52.214.46.0/]");

        // Enter username and password
        driver.findElement(By.name("username")).sendKeys("priya");
        driver.findElement(By.name("password")).sendKeys("Hcltech@123");

        // Click on Submit button
        driver.findElement(By.id("submitBtn")).click();

        // Verify landing on Card Management Application home page
        String homePageTitle = driver.getTitle();
        if (homePageTitle.equals("Card Management Application Home Page")) {
            System.out.println("User is on Card Management Application home page");
        } else {
            System.out.println("Failed to land on Card Management Application home page");
        }

        // Navigate to Account Update page
        driver.findElement(By.linkText("ACCOUNT UPDATE")).click();

        // Enter valid account ID and click on Search button
        driver.findElement(By.name("accountId")).sendKeys("2");
        driver.findElement(By.id("searchBtn")).click();

        // Verify account information retrieval
        String accountInfo = driver.findElement(By.id("accountInfo")).getText();
        if (!accountInfo.isEmpty()) {
            System.out.println("Account information retrieved successfully");
        } else {
            System.out.println("Failed to retrieve account information");
        }

        // Enter Credit Limit as "6150"
        driver.findElement(By.name("creditLimit")).sendKeys("6150");

        // Click on Save button
        driver.findElement(By.id("saveBtn")).click();

        // Verify account information update in the database
        String successMessage = driver.findElement(By.id("successMessage")).getText();
        if (successMessage.equals("Changes committed to database")) {
            System.out.println("Account information updated successfully");
        } else {
            System.out.println("Failed to update account information");
        }

        // Close the browser
        driver.quit();
    }
}