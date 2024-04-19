import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Guru {
    public static void main(String[] args) {
        // Set the path to the chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        // Open the Card Management Screen
        driver.get("http://52.214.46.0/");

        // Find and enter the username
        WebElement usernameField = driver.findElement(By.xpath("//input[@name='username']"));
        usernameField.sendKeys("priya");

        // Find and enter the password
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("Hcltech@123");

        // Find and click on the Submit button
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
        
        // Verify landing on Card Management Application home page
        String expectedHomePageTitle = "Card Management Application";
        String actualHomePageTitle = driver.getTitle();
        if (actualHomePageTitle.equals(expectedHomePageTitle)) {
            System.out.println("Successfully landed on Card Management Application home page");
        } else {
            System.out.println("Failed to land on Card Management Application home page");
        }

        // Navigate to the ACCOUNT UPDATE page
        WebElement accountUpdateLink = driver.findElement(By.xpath("//a[contains(text(),'ACCOUNT UPDATE')]"));
        accountUpdateLink.click();

        // Find and enter a valid account ID
        WebElement accountIdField = driver.findElement(By.xpath("//input[@name='accountId']"));
        accountIdField.sendKeys("2");

        // Find and click on the Search button
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
        
        // Verify account information retrieval
        // ...

        // Enter Credit Limit as "6150"
        WebElement creditLimitField = driver.findElement(By.xpath("//input[@name='creditLimit']"));
        creditLimitField.clear();
        creditLimitField.sendKeys("6150");

        // Find and click on the Save button
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();
        
        // Verify successful account information update
        // ...

        // Verify success pop up message
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(text(),'Changes committed to database')]"));
        String expectedSuccessMessage = "Changes committed to database";
        String actualSuccessMessage = successMessage.getText();
        if (actualSuccessMessage.equals(expectedSuccessMessage)) {
            System.out.println("Success message displayed: " + actualSuccessMessage);
        } else {
            System.out.println("Failed to display success message");
        }

        // Close the browser
        driver.quit();
    }
}