Here is the Selenium Java Automation Test script for the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomationTest {

    public static void main(String[] args) {
        // Set the system property for chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the given URL
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set the browser name as "Customer Login"
        String browserName = "Customer Login";

        // Set the page name as "Customer Login"
        String pageName = "Customer Login";

        // Enter the email address in the "Email" field
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");

        // Enter the password in the "Password" field
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.className("gear"))).perform();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Click on the "Driven Backpack" image
        driver.findElement(By.xpath("//img[@alt='Driven Backpack']")).click();

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        String orderSummary = driver.findElement(By.className("product-name")).getText();
        if (orderSummary.contains("Driven Backpack")) {
            System.out.println("Order Summary contains Driven Backpack product");
        } else {
            System.out.println("Order Summary does not contain Driven Backpack product");
        }

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Click on the "New Address" button
        driver.findElement(By.id("billing-new-address-form")).click();

        // Enter the address details
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");
        driver.findElement(By.id("billing:city")).sendKeys("Texas");
        driver.findElement(By.id("billing:region_id")).sendKeys("Texas");
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.id("billing-buttons-container")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).click();

        // Verify the message "Thank you for your purchase!"
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("success-msg")));
        String successMessage = driver.findElement(By.className("success-msg")).getText();
        if (successMessage.equals("Thank you for your purchase!")) {
            System.out.println("Successful purchase message is displayed");
        } else {
            System.out.println("Successful purchase message is not displayed");
        }

        // Click on the "Change" button
        driver.findElement(By.className("back-link")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }
}
```

Make sure to replace "path/to/chromedriver" with the actual path to your chrome driver installation.

This script covers all the steps and test validations mentioned in the given feature file scenario. Let me know if you need any further assistance.