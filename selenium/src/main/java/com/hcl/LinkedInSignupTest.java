```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkedInSignupTest {
    public static void main(String[] args) throws InterruptedException {
        // Set the driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Open the web browser and navigate to LinkedIn
            driver.get("https://www.linkedin.com/");
            Thread.sleep(2000);

            // Step 2: Click on "New to LinkedIn? Join now" button
            WebElement joinNowButton = driver.findElement(By.linkText("Join now"));
            joinNowButton.click();
            Thread.sleep(2000);

            // Step 3: Verify that the user is redirected to the "Create account" page
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("/signup")) {
                throw new AssertionError("Not redirected to Create account page.");
            }

            // Step 4: Enter a valid email or phone number
            WebElement emailField = driver.findElement(By.id("email-or-phone"));
            emailField.sendKeys("testuser123@example.com"); // Use a unique email

            // Step 5: Enter a desired password
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("TestPassword123!");

            // Step 6: Click on the "Agree & Join" button
            WebElement agreeJoinButton = driver.findElement(By.xpath("//button[contains(text(),'Agree & Join')]"));
            agreeJoinButton.click();
            Thread.sleep(3000);

            // Step 7: Verify redirection to confirmation page
            String confirmUrl = driver.getCurrentUrl();
            if (!confirmUrl.contains("check-inbox") && !confirmUrl.contains("registration")) {
                throw new AssertionError("User not redirected to the confirmation page after signup.");
            }

            System.out.println("LinkedIn signup test completed successfully.");
        } finally {
            driver.quit();
        }
    }
}
```