Here is the Selenium Java automation test script for the given scenario:

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MagentoOrderAutomation {
    public static void main(String[] args) {
        // Set system property for Chrome driver
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");

        // Create a new instance of Chrome driver
        WebDriver driver = new ChromeDriver();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Enter email and password for login
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Mouse hover on the "Gear" menu
        Actions action = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("gear-icon"));
        action.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Click on the "Overnight Duffle" image
        driver.findElement(By.linkText("Overnight Duffle")).click();

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Verify that the "Order Summary" is having "Overnight Duffle" product
        String orderSummary = driver.findElement(By.className("order-summary")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("Order Summary contains Overnight Duffle product");
        } else {
            System.out.println("Order Summary does not contain Overnight Duffle product");
        }

        // Click on the "New Address" button
        driver.findElement(By.id("shipping-address-button")).click();

        // Enter shipping address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        Select stateDropdown = new Select(driver.findElement(By.id("region_id")));
        stateDropdown.selectByVisibleText("Texas");
        driver.findElement(By.id("postcode")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.className("shipping-address-item")).findElement(By.className("primary")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-button")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:same_as_shipping")).click();

        // Click on the "Place Order" button
        driver.findElement(By.id("review-button")).click();

        // Verify the success message
        String successMessage = driver.findElement(By.className("success-msg")).getText();
        if (successMessage.contains("Thank you for your purchase!")) {
            System.out.println("Success message is displayed");
        } else {
            System.out.println("Success message is not displayed");
        }

        // Click on the "Change" button
        driver.findElement(By.linkText("Change")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}
```

Make sure to replace `"path_to_chrome_driver"` with the actual path to your Chrome driver. Also, please note that you might need to add the necessary imports for the Selenium and Java Actions classes, as well as the WebDriver and WebElement interfaces.