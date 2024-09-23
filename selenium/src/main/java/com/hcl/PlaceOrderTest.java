
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PlaceOrderTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void placeOrderTest() {
        // Navigate to the given URL
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set browser name as "Customer Login"
        driver.executeScript("window.scrollBy(0,250)");
        driver.executeScript("document.getElementById('customer-login').textContent = 'Customer Login';");

        // Set page name as "Customer Login"
        driver.executeScript("window.scrollBy(0,250)");
        driver.executeScript("document.getElementById('customer-login').setAttribute('data-u-target', 'Customer Login')");

        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Mouse hover on the "Gear" menu
        driver.findElement(By.className("account-cart-wrapper")).click();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Click on the "Driven Backpack" image
        driver.findElement(By.cssSelector("a[href*='driven-backpack.html']")).click();

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        String orderSummaryText = driver.findElement(By.className("product-name")).getText();
        assert orderSummaryText.equals("Driven Backpack"): "Order Summary does not have Driven Backpack product";

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-continue")).click();

        // Click on the "New Address" button
        driver.findElement(By.id("billing-new-address-form")).click();

        // Enter address details
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
        String purchaseConfirmationText = driver.findElement(By.cssSelector(".checkout-success h1 span")).getText();
        assert purchaseConfirmationText.equals("Thank you for your purchase!"): "Purchase confirmation message not displayed";

        // Click on the "Change" button
        driver.findElement(By.linkText("Change")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }

    @AfterTest
    public void cleanup() {
        // Close the browser
        driver.quit();
    }
}
```

Note: 
1. Make sure to download and set the correct path for the ChromeDriver executable.
2. This code assumes you have the necessary dependencies (Selenium Java and TestNG) added to your project.

Let me know if you need any further assistance!