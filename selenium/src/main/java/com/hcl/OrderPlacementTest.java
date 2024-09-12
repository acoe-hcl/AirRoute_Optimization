import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderPlacementTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up Selenium WebDriver and open Magento website
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }

    @Test
    public void testOrderPlacement() {
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Verify browser name and page name
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        // Enter email address and password
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Hover over the "Gear" menu and click on the "Bags" link
        // Your code to perform hover and click here

        // Click on the image of the "Driven Backpack"
        // Your code to click on the image here

        // Click on the "Add to Cart" button
        // Your code to click on the "Add to Cart" button here

        // Click on the "My Cart" link
        // Your code to click on the "My Cart" link here

        // Verify that "Driven Backpack" is included in the "Order Summary"
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Driven Backpack']")).isDisplayed());

        // Click on the "Proceed to Checkout" button
        // Your code to click on the "Proceed to Checkout" button here

        // Click on the "New Address" button
        // Your code to click on the "New Address" button here

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("123 Main Street");
        driver.findElement(By.id("city")).sendKeys("City");
        driver.findElement(By.id("region_id")).sendKeys("State/Province");
        driver.findElement(By.id("postcode")).sendKeys("Postal Code");
        driver.findElement(By.id("telephone")).sendKeys("Phone Number");

        // Click on the "Ship Here" button
        driver.findElement(By.id("shipping-buttons-container")).findElement(By.tagName("button")).click();

        // Select the "Fixed" radio button
        // Your code to select the "Fixed" radio button here

        // Click on the "Next" button
        // Your code to click on the "Next" button here

        // Select the "My billing and shipping address are the same" checkbox
        // Your code to select the checkbox here

        // Click on the "Place Order" button
        // Your code to click on the "Place Order" button here

        // Verify confirmation message
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Thank you for your purchase!"));

        // Click on the "Change" button
        // Your code to click on the "Change" button here

        // Click on the "Signout" link
        // Your code to click on the "Signout" link here
    }
}