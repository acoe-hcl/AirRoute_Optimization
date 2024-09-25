As a professional code assistant, I can help you in generating the Test Automation Script for the given scenario using Selenium with Java and TestNG.

```java
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the system property for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the Chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

    @Test
    public void placeOrderForDrivenBackpack() {
        // Given User navigates to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // When User clicks on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Then Verify browser name is set as "Customer Login"
        String browserName = driver.getTitle();
        Assert.assertEquals(browserName, "Customer Login");

        // Then Verify page name is set as "Customer Login"
        String pageName = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(pageName, "Customer Login");

        // When User enters email and password
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // And User clicks on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // When User mouse hovers on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("my-account"));
        actions.moveToElement(gearMenu).perform();

        // And User clicks on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // When User clicks on the "Driven Backpack" image
        driver.findElement(By.linkText("Driven Backpack")).click();

        // When User clicks on the "Add to Cart" button
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();

        // And User clicks on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // And Verify that the "Order Summary" has "Driven Backpack" product
        String orderSummary = driver.findElement(By.className("subtotal")).getText();
        Assert.assertTrue(orderSummary.contains("Driven Backpack"));

        // And User clicks on the "Proceed to Checkout" button
        driver.findElement(By.xpath("//button[contains(text(), 'Proceed to Checkout')]")).click();

        // When User clicks on the "New Address" button
        driver.findElement(By.id("billing-new-address-form")).click();

        // And User enters address details
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");
        driver.findElement(By.id("billing:city")).sendKeys("Texas");
        driver.findElement(By.id("billing:region_id")).sendKeys("Texas");
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");

        // And User clicks on the "Ship Here" button
        driver.findElement(By.id("billing-buttons-container")).click();

        // And User selects the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // And User clicks on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).click();

        // And User selects the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // And User clicks on the "Place Order" button
        driver.findElement(By.xpath("//button[contains(text(), 'Place Order')]")).click();

        // And Verify the message "Thank you for your purchase!"
        String successMessage = driver.findElement(By.className("success-msg")).getText();
        Assert.assertEquals(successMessage, "Thank you for your purchase!");

        // When User clicks on the "Change" button
        driver.findElement(By.xpath("//button[contains(text(), 'Change')]")).click();

        // And User clicks on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }
}
```

This is a basic example to get you started with the Test Automation Script for the given scenario. Depending on your specific requirements and the implementation of the web application, further modifications may be required. Please make sure to update the `path/to/chromedriver` with the actual path to the ChromeDriver executable on your system.