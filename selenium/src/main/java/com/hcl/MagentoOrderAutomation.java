Here is a sample Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Set the path of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        
        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");
        
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Set the browser name as "Customer Login" and page name as "Customer Login"
        driver.executeScript("window.name = 'Customer Login'");
        driver.executeScript("document.title = 'Customer Login'");
        
        // Enter the email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        
        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
        
        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("gear"));
        actions.moveToElement(gearMenu).perform();
        
        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
        
        // Click on the "Driven Backpack" image
        driver.findElement(By.cssSelector("a[href='https://magento.softwaretestingboard.com/index.php/driven-backpack.html']")).click();
        
        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
        
        // Click on the "My Cart" link
        driver.findElement(By.cssSelector("a[title='My Cart']")).click();
        
        // Verify that the "Order Summary" is having "Driven Backpack" product
        String orderSummary = driver.findElement(By.className("order-summary")).getText();
        if (orderSummary.contains("Driven Backpack")) {
            System.out.println("Order Summary contains Driven Backpack product.");
        } else {
            System.out.println("Order Summary does not contain Driven Backpack product.");
        }
        
        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();
        
        // Click on the "New Address" button
        driver.findElement(By.id("billing-new-address-form")).click();
        
        // Enter the address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        
        // Click on the "Ship Here" button
        driver.findElement(By.cssSelector("button[title='Ship Here']")).click();
        
        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        
        // Click on the "Next" button
        driver.findElement(By.cssSelector("button[title='Next']")).click();
        
        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing\\:use_for_shipping_yes")).click();
        
        // Click on the "Place Order" button
        driver.findElement(By.cssSelector("button[title='Place Order']")).click();
        
        // Verify the message "Thank you for your purchase!"
        String thankYouMessage = driver.findElement(By.className("success-msg")).getText();
        if (thankYouMessage.contains("Thank you for your purchase!")) {
            System.out.println("Purchase was successful.");
        } else {
            System.out.println("Purchase was not successful.");
        }
        
        // Click on the "Change" button
        driver.findElement(By.cssSelector("button[title='Change']")).click();
        
        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
        
        // Close the browser
        driver.quit();
    }
}
```

Please note that this is just a sample script and you may need to modify it according to your specific requirements and environment setup.