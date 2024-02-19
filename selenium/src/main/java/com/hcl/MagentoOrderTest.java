import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class MagentoOrderTest {

    WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path_to_chrome_driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void tearDown() {
        driver.quit();
    }

    public void placeOrderTest() {
        setUp();

        // Launch Application
        driver.get("https://magento.softwaretestingboard.com/");

        // Sign In
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();

        // Navigate to Gear > Bags
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("gear"))).perform();
        driver.findElement(By.linkText("Bags")).click();

        // Select Product and Add to Cart
        driver.findElement(By.xpath("//img[@alt='Overnight Duffle']")).click();
        driver.findElement(By.id("product-addtocart-button")).click();
        driver.findElement(By.linkText("My Cart")).click();
        driver.findElement(By.id("proceed-checkout")).click();

        // Verify Product in Cart
        String product = driver.findElement(By.xpath("//td[@class='product-item-name']")).getText();
        Assert.assertEquals(product, "Overnight Duffle");

        // Enter Address and Place Order
        driver.findElement(By.id("new-address")).click();
        driver.findElement(By.id("street")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region")).selectByVisibleText("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("phone")).sendKeys("3456788765");
        driver.findElement(By.id("ship-here")).click();
        driver.findElement(By.id("fixed")).click();
        driver.findElement(By.id("same-address")).click();
        driver.findElement(By.id("place-order")).click();

        // Verify Success Message
        String successMsg = driver.findElement(By.id("success-msg")).getText();
        Assert.assertEquals(successMsg, "Thank you for your purchase!");

        // Sign Out
        driver.findElement(By.id("change")).click();
        driver.findElement(By.linkText("Signout")).click();

        tearDown();
    }

    public static void main(String[] args) {
        MagentoOrderTest test = new MagentoOrderTest();
        test.placeOrderTest();
    }
}
```

