										import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test7 {
    public static void main(String[] args) {
        // Set the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the LinkedIn homepage
        driver.get("https://www.linkedin.com");

        // Find the username field and enter the username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("acoe.hcl@gmail.com");

        // Find the password field and enter the password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("hcltech@123");

        // Find the sign in button and click it
        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();

        // Verify that the user is on the LinkedIn feed page
        String expectedTitle = "LinkedIn";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Sign in successful");
        } else {
            System.out.println("Sign in failed");
        }

        // Close the browser
        driver.quit();
    }
}
									