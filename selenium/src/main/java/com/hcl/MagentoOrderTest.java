
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    WebDriver driver;

    @Test
    public void placeOrderTest() {
        // Initialize WebDriver

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Sign in
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();

        // Navigate to Bags - Gear
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.xpath("//*[@id='ui-id-5']"));
        actions.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();

        // Select Overnight Duffle
        driver.findElement(By.xpath("//a[@title='Overnight Duffle']")).click();
        driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();

        // Proceed to Checkout
        driver.findElement(By.linkText("My Cart")).click();
        driver.findElement(By.xpath("//span[text()='Proceed to Checkout']")).click();

        // Verify Order Summary
        WebElement orderSummary = driver.findElement(By.xpath("//ol[@id='checkout-summary']"));
        Assert.assertTrue(orderSummary.getText().contains("Overnight Duffle"));

        // Enter shipping details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        new Select(driver.findElement(By.id("region_id"))).selectByVisibleText("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");
        driver.findElement(By.id("ship_here")).click();

        // Select Fixed shipping method and proceed
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        driver.findElement(By.xpath("//span[text()='Next']")).click();

        // Place Order
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        driver.findElement(By.xpath("//span[text()='Place Order']")).click();

        // Verify Success Page
        WebElement successMsg = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        Assert.assertEquals(successMsg.getText(), "Thank you for your purchase!");

        // Sign Out
        driver.findElement(By.linkText("Sign Out")).click();
    }
}
