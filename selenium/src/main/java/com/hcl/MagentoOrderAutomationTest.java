Sure! Here's the Selenium Java automation test script file for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderAutomationTest {
    public static void main(String[] args) {
        // Set the browser driver path
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Step 1: Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Step 2: Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Step 3: Enter email and password
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Step 4: Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Step 5: Mouse hover on the "Gear" menu and click on the "Bags" link
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.id("gear"));
        actions.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();

        // Step 6: Click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();

        // Step 7: Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Step 8: Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Step 9: Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Step 10: Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.xpath("//h2[contains(text(),'Order Summary')]/following-sibling::p")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains 'Overnight Duffle' product.");
        } else {
            System.out.println("Order Summary does not contain 'Overnight Duffle' product.");
        }

        // Step 11: Click on the "New Address" button
        driver.findElement(By.id("billing-new-address-form")).click();

        // Step 12: Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Step 13: Click on the "Ship Here" button
        driver.findElement(By.xpath("//span[text()='Ship Here']/parent::button")).click();

        // Step 14: Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Step 15: Click on the "Next" button
        driver.findElement(By.id("billing-buttons-container")).click();

        // Step 16: Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Step 17: Click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).click();

        // Step 18: Verify the success message
        String successMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]")).getText();
        if (successMessage.contains("Thank you for your purchase!")) {
            System.out.println("Success message displayed: " + successMessage);
        } else {
            System.out.println("Success message not displayed.");
        }

        // Step 19: Click on the "Change" button
        driver.findElement(By.id("shipping-new-address-form")).click();

        // Step 20: Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}
```

Make sure to replace "/path/to/chromedriver" with the actual path to your ChromeDriver executable file.

Please note that this is a basic implementation and may require modifications to suit your specific testing needs.