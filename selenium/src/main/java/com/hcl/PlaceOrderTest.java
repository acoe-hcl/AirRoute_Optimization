Sure! Here's a sample Selenium Java TestNG code that automates the given scenario:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up Chrome driver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Create a new ChromeDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void placeOrderTest() {
        // Given User navigates to "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");

        // When I click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Then I should set the browser name as "Customer Login"
        String browserName = driver.getCurrentUrl();
        Assert.assertTrue(browserName.contains("Customer Login"));

        // And I should set the page name as "Customer Login"
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Customer Login");

        // When I enter "testermail@gmail.com" in the "Email" field
        driver.findElement(By.name("login[username]")).sendKeys("testermail@gmail.com");

        // And I enter "Tester@123" in the "Password" field as secure text
        driver.findElement(By.name("login[password]")).sendKeys("Tester@123");

        // And I click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // When I mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.cssSelector(".gear"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // And I click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // When I click on the "Driven Backpack" image
        driver.findElement(By.xpath("//a[contains(text(), 'Driven Backpack')]")).click();

        // When I click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // And I click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // And I verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummaryProduct = driver.findElement(By.xpath("//td[@class='product-name']"));
        String productText = orderSummaryProduct.getText();
        Assert.assertTrue(productText.contains("Driven Backpack"));

        // And I click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // When I click on the "New Address" button
        driver.findElement(By.id("billing_add_new_address")).click();

        // And I enter "4 South Street" in the "Street" field
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");

        // And I enter "Texas" in the "City" field
        driver.findElement(By.id("billing:city")).sendKeys("Texas");

        // And I select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        // And I enter "77567" in the "Zip/Postal Code" field
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");

        // And I enter "3456788765" in the "Phone Number" field
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");

        // And I click on the "Ship Here" button
        driver.findElement(By.id("billing-shipping-method-form")).submit();

        // And I select the "Fixed" radio button
        driver.findElement(By.id("p_method_checkmo")).click();

        // And I click on the "Next" button
        driver.findElement(By.cssSelector(".button.btn-proceed-checkout.btn-next")).click();

        // And I select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // And I click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).submit();

        // And I verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.cssSelector(".checkout-success p.success-msg"));
        String messageText = successMessage.getText();
        Assert.assertEquals(messageText, "Thank you for your purchase!");

        // When I click on the "Change" button
        driver.findElement(By.id("change-billing-address")).click();

        // And I click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
```

This code uses Selenium WebDriver with ChromeDriver to automate the testing of the given scenario. It sets up the Chrome driver, navigates to the Magento website, performs various actions like clicking on links and buttons, filling in form fields, and asserting the expected results.

Please note that you will need to download and configure the ChromeDriver to match your system's environment. Also, make sure to import the necessary Selenium and TestNG libraries in your project.

Hope this helps! Let me know if you have any questions.