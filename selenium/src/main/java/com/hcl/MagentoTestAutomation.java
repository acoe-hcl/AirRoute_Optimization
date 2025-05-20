import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MagentoTestAutomation {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver (ensure that the chromedriver executable is in your system path)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        // Open the Magento website
        driver.get("http://example.com"); // Replace with actual Magento URL
    }

    @Test
    public void testMagentoPurchaseFlow() {
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Verify that the browser name and page title is "Customer Login"
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Customer Login");
        System.out.println("Page title verified: " + pageTitle);

        // Enter credentials and submit the form
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();

        // Hover over the "Gear" menu and click on the "Bags" link
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.cssSelector(".gear-menu-selector")); // Update selector
        actions.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();

        // Click on the image of the "Driven Backpack" product
        driver.findElement(By.cssSelector("img[alt='Driven Backpack']")).click(); // Update selector

        // Click on the "Add to Cart" button
        driver.findElement(By.id("add-to-cart-button")).click(); // Update selector

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Verify that the "Order Summary" includes the "Driven Backpack" product
        String cartContents = driver.findElement(By.cssSelector(".cart-summary-selector")).getText(); // Update selector
        Assert.assertTrue(cartContents.contains("Driven Backpack"), "Order Summary does not contain 'Driven Backpack'");
        
        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("checkout-button")).click(); // Update selector

        // Click on the "New Address" button
        driver.findElement(By.id("new-address-button")).click(); // Update selector

        // Enter Mailing Address Information
        driver.findElement(By.id("street-address")).sendKeys("123 Test St");
        driver.findElement(By.id("city")).sendKeys("Test City");
        driver.findElement(By.id("state")).sendKeys("Test State");
        driver.findElement(By.id("zip")).sendKeys("12345");
        driver.findElement(By.id("phone")).sendKeys("1234567890");

        // Click on the "Ship Here" button
        driver.findElement(By.id("ship-here-button")).click(); // Update selector

        // Select the "Fixed" radio button
        driver.findElement(By.id("fixed-shipping")).click(); // Update selector

        // Click on the "Next" button
        driver.findElement(By.id("next-button")).click(); // Update selector

        // Select "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing-same-as-shipping")).click(); // Update selector

        // Click on the "Place Order" button
        driver.findElement(By.id("place-order-button")).click(); // Update selector

        // Verify that the confirmation message is displayed
        String confirmationMessage = driver.findElement(By.cssSelector(".confirmation-message-selector")).getText(); // Update selector
        Assert.assertEquals(confirmationMessage, "Thank you for your purchase!", "Confirmation message did not display correctly.");

        // Click on the "Change" button (optional step based on requirement)
        driver.findElement(By.id("change-button")).click(); // Update selector

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
