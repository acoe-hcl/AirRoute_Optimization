import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    WebDriver driver;

    @Test
    public void placeOrder() {
        System.setProperty("webdriver.chrome.driver", "path-to-chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Launch the application
        driver.get("https://magento.softwaretestingboard.com/");

        // Sign In
        driver.findElement(By.xpath("//a[text()='Sign In']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Tester@123");
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();

        // Navigate to Bags section
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.xpath("//a[text()='Gear']"));
        actions.moveToElement(gearMenu).perform();
        driver.findElement(By.xpath("//a[text()='Bags']")).click();

        // Select product and add to cart
        driver.findElement(By.xpath("//img[@alt='Overnight Duffle']")).click();
        driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();
        driver.findElement(By.xpath("//a[text()='My Cart']")).click();
        driver.findElement(By.xpath("//button[text()='Proceed to Checkout']")).click();

        // Verify product in Order Summary
        String product = driver.findElement(By.xpath("//li[contains(text(), 'Overnight Duffle')]")).getText();
        Assert.assertEquals(product, "Overnight Duffle");

        // Add new address and proceed to checkout
        driver.findElement(By.xpath("//button[text()='New Address']")).click();
        driver.findElement(By.xpath("//input[@id='Street']")).sendKeys("4 South Street");
        driver.findElement(By.xpath("//input[@id='City']")).sendKeys("Texas");
        driver.findElement(By.xpath("//select[@id='State/Province']")).sendKeys("Texas");
        driver.findElement(By.xpath("//input[@id='Zip/Postal Code']")).sendKeys("77567");
        driver.findElement(By.xpath("//input[@id='Phone Number']")).sendKeys("3456788765");
        driver.findElement(By.xpath("//button[text()='Ship Here']")).click();
        driver.findElement(By.xpath("//input[@id='Fixed']")).click();
        driver.findElement(By.xpath("//button[text()='Next']")).click();
        driver.findElement(By.xpath("//input[@id='same_as_shipping']")).click();
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        // Verify success message
        String successMsg = driver.findElement(By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]")).getText();
        Assert.assertEquals(successMsg, "Thank you for your purchase!");

        // Sign out
        driver.findElement(By.xpath("//button[text()='Change']")).click();
        driver.findElement(By.xpath("//a[text()='Signout']")).click();

        // Close browser
        driver.quit();
    }
}