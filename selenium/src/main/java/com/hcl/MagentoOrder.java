
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrder {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "<Path of the chrome driver>");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);

        try {
            // Launch Application
            driver.get("https://magento.softwaretestingboard.com/");

            // Login
            driver.findElement(By.linkText("Sign In")).click();
            driver.findElement(By.id("Email")).sendKeys("autotest567@gmail.com");
            driver.findElement(By.id("Password")).sendKeys("Tester@123");
            driver.findElement(By.xpath("//button[.='Sign In']")).click();

            // Place order 
            action.moveToElement(driver.findElement(By.id("Gear"))).build().perform();
            driver.findElement(By.linkText("Bags")).click();
            driver.findElement(By.id("Overnight Duffle")).click();
            driver.findElement(By.id("Add to Cart")).click();
            driver.findElement(By.linkText("My Cart")).click();
            driver.findElement(By.id("Proceed to Checkout")).click();

            // Order Summary
            String actualProduct = driver.findElement(By.id("Order Summary")).getText();
            assert("Overnight Duffle".equals(actualProduct));

            // New Address
            driver.findElement(By.id("New Address")).click();
            driver.findElement(By.id("Street")).sendKeys("4 South Street");
            driver.findElement(By.id("City")).sendKeys("Texas");
            driver.findElement(By.id("State/Province")).sendKeys("Texas");
            driver.findElement(By.id("Zip/Postal Code")).sendKeys("77567");
            driver.findElement(By.id("Phone Number")).sendKeys("3456788765");
            driver.findElement(By.id("Ship Here")).click();
            driver.findElement(By.id("Fixed")).click();
            driver.findElement(By.id("Next")).click();

            // Billing Address
            driver.findElement(By.id("My billing and shipping address are the same")).click();
            driver.findElement(By.id("Place Order")).click();
            
            // Order Confirmation
            String actualMessage = driver.findElement(By.xpath("//div[.='Thank you for your purchase!']")).getText();
            assert("Thank you for your purchase!".equals(actualMessage));

            // Sign Out
            driver.findElement(By.id("Change")).click();
            driver.findElement(By.linkText("Signout")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Element Not Found: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
```

This is a generic code and might need some modifications based on the actual attributes of the Web Elements. Make sure to replace `<Path of the chrome driver>` with the actual location path of `chromedriver.exe` on your system. Also, the actual site might have CAPTCHA for login, which can't be bypassed using Selenium. Please add necessary waits according to the page loading times.