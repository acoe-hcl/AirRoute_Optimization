Here is the Selenium Java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class TestAutomationScript {
    public static void main(String[] args) {
        // Set the path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Set implicit wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        // Set browser and page names
        String browserName = "Home Page";
        String pageName = "Home Page";
        
        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Set browser and page names
        browserName = "Customer Login";
        pageName = "Customer Login";
        
        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // Set browser and page names
        browserName = "Home Page";
        pageName = "Home Page";
        
        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        
        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // Set browser and page names
        browserName = "Bags - Gear";
        pageName = "Bags - Gear";
        
        // Click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();
        
        // Set browser and page names
        browserName = "Overnight Duffle";
        pageName = "Overnight Duffle";
        
        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
        
        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        
        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();
        
        // Set browser and page names
        browserName = "Checkout";
        pageName = "Checkout";
        
        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.className("product-name")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product.");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product.");
        }
        
        // Click on the "New Address" button
        driver.findElement(By.linkText("New Address")).click();
        
        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        driver.findElement(By.id("shipping-address-form")).submit();
        
        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        
        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).submit();
        
        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        
        // Click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).submit();
        
        // Set browser and page names
        browserName = "Success Page";
        pageName = "Success Page";
        
        // Verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.className("success-msg")).getText();
        if (successMessage.contains("Thank you for your purchase!")) {
            System.out.println("Order placed successfully.");
        } else {
            System.out.println("Order placement failed.");
        }
        
        // Click on the "Change" button
        driver.findElement(By.linkText("Change")).click();
        
        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        
        // Quit the browser
        driver.quit();
    }
}
```

Make sure to replace "path/to/chromedriver.exe" with the actual path to the chromedriver executable on your system.