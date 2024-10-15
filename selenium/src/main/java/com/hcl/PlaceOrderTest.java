import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class PlaceOrderTest {

    WebDriver driver;

    @Test
    public void testPlaceOrder() {
        // Set browser properties
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();

        // Navigate to the website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set browser name as "Customer Login"
        driver.manage().window().setName("Customer Login");

        // Set page name as "Customer Login"
        driver.findElement(By.tagName("h1")).setText("Customer Login");

        // Enter email
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");

        // Enter password
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Mouse hover on the "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.linkText("Gear"));
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();

        // Click on the "Driven Backpack" image
        driver.findElement(By.linkText("Driven Backpack")).click();

        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();

        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        String orderSummary = driver.findElement(By.className("order-summary")).getText();
        Assert.assertTrue(orderSummary.contains("Driven Backpack"), "Order summary doesn't contain Driven Backpack");

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.linkText("Proceed to Checkout")).click();

        // Click on the "New Address" button
        driver.findElement(By.className("add-new-address")).click();

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.id("shipping-dialog-submit")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();

        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();

        // Click on the "Place Order" button
        driver.findElement(By.id("review-buttons-container")).click();

        // Verify the message "Thank you for your purchase!"
        String confirmationMessage = driver.findElement(By.className("success-msg")).getText();
        Assert.assertTrue(confirmationMessage.contains("Thank you for your purchase!"), "Confirmation message not displayed");

        // Click on the "Change" button
        driver.findElement(By.className("change")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();

        // Close the browser
        driver.quit();
    }

}
