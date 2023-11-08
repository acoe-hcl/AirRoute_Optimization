import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignInTest {
    public static void main(String[] args) {
        // Setting up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "C://Users//Public//chromedriver.exe");

        // Creating a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigating to the LinkedIn homepage
        driver.get("https://www.linkedin.com");

        // Finding the username field and entering the username
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("acoe.hcl@gmail.com");

        // Finding the password field and entering the password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("hcltech@123");

        // Finding the Sign in button and clicking it
        WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        signInButton.click();

        // Verifying that the user is on the LinkedIn feed page
        String expectedTitle = "LinkedIn";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        // Closing the browser
        driver.quit();
    }
}
									