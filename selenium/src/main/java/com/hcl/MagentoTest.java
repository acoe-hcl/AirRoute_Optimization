import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MagentoTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "<path-to-your-chromedriver>");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testMagentoPurchaseFlow() throws InterruptedException {
        // Step 1: Open the Magento website
        driver.get("https://your-magento-website-url.com");
        
        // Step 2: Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        
        // Step 3: Verify that the browser name and page name is set as "Customer Login"
        String expectedTitle = "Customer Login";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");

        // Step 4: Enter the email address and password
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Step 5: Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Step 6: Hover over the "Gear" menu and click on the "Bags" link
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.cssSelector("selector-for-gear-menu"));
        actions.moveToElement(gearMenu).perform();  // Hover over the gear menu
        driver.findElement(By.linkText("Bags")).click();

        // Step 7: Click on the image of the "Driven Backpack" product
        driver.findElement(By.cssSelector("selector-for-driven-backpack-image")).click();

        // Step 8: Click on the "Add to Cart" button
        driver.findElement(By.id("add-to-cart-button")).click();

        // Step 9: Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Step 10: Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.cssSelector("selector-for-order-summary"));
        Assert.assertTrue(orderSummary.getText().contains("Driven Backpack"), "Order Summary does not contain the product!");

        // Step 11: Click on the "Proceed to Checkout" button
        driver.findElement(By.cssSelector("selector-for-proceed-to-checkout")).click();

        // Step 12: Click on the "New Address" button
        driver.findElement(By.cssSelector("selector-for-new-address")).click();

        // Step 13: Enter the address details
        driver.findElement(By.id("street-address")).sendKeys("123 Test St");
        driver.findElement(By.id("city")).sendKeys("Test City");
        driver.findElement(By.id("state")).sendKeys("Test State");
        driver.findElement(By.id("zip")).sendKeys("12345");
        driver.findElement(By.id("phone")).sendKeys("1234567890");

        // Step 14: Click on the "Ship Here" button
        driver.findElement(By.cssSelector("selector-for-ship-here")).click();

        // Step 15: Select the "Fixed" radio button
        driver.findElement(By.cssSelector("selector-for-fixed-radio")).click();

        // Step 16: Click on the "Next" button
        driver.findElement(By.cssSelector("selector-for-next-button")).click();

        // Step 17: Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.cssSelector("selector-for-same-address-checkbox")).click();

        // Step 18: Click on the "Place Order" button
        driver.findElement(By.cssSelector("selector-for-place-order")).click();

        // Step 19: Verify that a confirmation message saying "Thank you for your purchase!" is displayed
        WebElement confirmationMessage = driver.findElement(By.cssSelector("selector-for-confirmation-message"));
        Assert.assertTrue(confirmationMessage.isDisplayed(), "Confirmation message is not displayed!");

        // Step 20: Click on the "Change" button (if applicable)
        driver.findElement(By.cssSelector("selector-for-change-button")).click();

        // Step 21: Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the test is complete
        driver.quit();
    }
}
