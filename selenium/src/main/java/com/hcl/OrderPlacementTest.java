
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class OrderPlacementTest {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "https://magento.softwaretestingboard.com/";
    }

    @Test
    public void testOrderPlacement() {
        driver.get(baseUrl);
        // Sign In steps
        driver.get(baseUrl + "customer/account/login/");
        driver.findElement(By.id("email")).sendKeys("autotest567@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();

        // Order Product steps:
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.linkText("Gear"))).perform();
        driver.findElement(By.linkText("Bags")).click();
        driver.findElement(By.xpath("//img[@alt='Overnight Duffle']")).click();
        driver.findElement(By.xpath("//button/span/span[text()='Add to Cart']")).click();
        driver.findElement(By.linkText("My Cart")).click();
        driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();

        // Checkout steps:
        driver.findElement(By.xpath("//button[@title='Place Order']")).click();

        // Verify Success Message:
        String actualMessage = driver.findElement(By.xpath("xpath-of-success-message")).getText();
        String expectedMessage = "Thank you for your purchase!";
        assertEquals(expectedMessage, actualMessage);

        //Sign out
        action.moveToElement(driver.findElement(By.linkText("Change"))).perform();
        driver.findElement(By.linkText("Signout")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
```

Please remember to replace the `"path/to/chromedriver"` with the actual location of your Chrome driver file. Also, note that you have to replace the `xpath-of-success-message` with the actual Xpath of the element showing success message after purchase.

This script doesn't cover address entry and some navigational steps, but those could be included by finding the appropriate element locators and including in your test script.