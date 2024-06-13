Sure! Here's a sample code for generating a Test Automation Script for the given scenario using Selenium WebDriver in Java:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MagentoOrderAutomation {

    public static void main(String[] args) {
        // Set the system property for the Chrome driver
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Launch the Magento application
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the Sign In link
        driver.findElement(By.linkText("Sign In")).click();

        // Enter the Email and Password credentials
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the Sign In button
        driver.findElement(By.id("send2")).click();

        // Mouse hover on the Gear menu and click on the Bags link
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.className("gear"))).perform();
        driver.findElement(By.linkText("Bags")).click();

        // Click on the Overnight Duffle image
        driver.findElement(By.linkText("Overnight duffle")).click();

        // Click on the Add to Cart button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the My Cart link
        driver.findElement(By.partialLinkText("My Cart")).click();

        // Click on the Proceed to Checkout button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Verify that the Order Summary contains the Overnight Duffle product
        String orderSummary = driver.findElement(By.className("product-name")).getText();
        if (orderSummary.contains("Overnight Duffle")) {
            System.out.println("The Order Summary contains Overnight Duffle product.");
        } else {
            System.out.println("The Order Summary does not contain Overnight Duffle product.");
        }

        // Click on the New Address button
        driver.findElement(By.id("billing-address-select")).sendKeys("New Address");

        // Enter the shipping address details
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");
        driver.findElement(By.id("billing:city")).sendKeys("Texas");
        driver.findElement(By.id("billing:region_id")).sendKeys("Texas");
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");
        driver.findElement(By.id("billing-buttons-container")).click();

        // Select the Fixed radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the Next button
        driver.findElement(By.id("shipping-method-buttons-container")).click();

        // Select the My billing and shipping address are the same checkbox
        driver.findElement(By.id("billing-address-same-as-shipping")).click();

        // Click on the Place Order button
        driver.findElement(By.id("review-buttons-container")).click();

        // Verify the Success message
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".checkout-success p"), "Thank you for your purchase!"));

        // Click on the Change button
        driver.findElement(By.linkText("Change")).click();

        // Click on the Signout link
        driver.findElement(By.linkText("Sign Out")).click();

        // Close the browser
        driver.quit();
    }
}
```

Make sure to replace "path_to_chromedriver" with the actual path to the ChromeDriver executable on your machine. Additionally, you'll need the Selenium WebDriver Java bindings and ChromeDriver to be set up in your project.