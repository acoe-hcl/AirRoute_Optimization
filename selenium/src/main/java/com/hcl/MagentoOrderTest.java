As a professional code assistant named Guru, I can help you generate the Test Automation Script for the given scenario using Selenium Java with TestNG. Here is the generated code:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up the WebDriver configuration
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        // Close the WebDriver after each test
        driver.quit();
    }

    @Test
    public void placeOrderForDrivenBackpackTest() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Set the browser name as "Customer Login"
        driver.manage().window().maximize();
        String browserName = driver.getTitle();
        Assert.assertEquals(browserName, "Customer Login");

        // Set the page name as "Customer Login"
        String pageName = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(pageName, "Customer Login");

        // Enter email and password
        driver.findElement(By.id("email")).sendKeys("testermail@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Tester@123");

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gear"));
        Actions actions = new Actions(driver);
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
        String orderSummary = driver.findElement(By.className("order-summary"))
                .getText();
        Assert.assertTrue(orderSummary.contains("Driven Backpack"));

        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();

        // Click on the "New Address" button
        driver.findElement(By.cssSelector("button[title='New Address']")).click();

        // Enter address details
        driver.findElement(By.id("street_1")).sendKeys("4 South Street");
        driver.findElement(By.id("city")).sendKeys("Texas");
        driver.findElement(By.id("region_id")).sendKeys("Texas");
        driver.findElement(By.id("zip")).sendKeys("77567");
        driver.findElement(By.id("telephone")).sendKeys("3456788765");

        // Click on the "Ship Here" button
        driver.findElement(By.id("shipping-save-address")).click();

        // Select the "Fixed" radio button
        driver.findElement(By.cssSelector("input[value='fixed']")).click();

        // Click on the "Next" button
        driver.findElement(By.cssSelector("button[title='Next']")).click();

        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing\\:same_as_shipping")).click();

        // Click on the "Place Order" button
        driver.findElement(By.cssSelector("button[title='Place Order']")).click();

        // Verify the message "Thank you for your purchase!"
        String thankYouMessage = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(thankYouMessage, "Thank you for your purchase!");

        // Click on the "Change" button
        driver.findElement(By.cssSelector("button[title='Change']")).click();

        // Click on the "Signout" link
        driver.findElement(By.linkText("Signout")).click();
    }
}
```

This code covers the given scenario of placing an order for the Driven Backpack product on Magento. It uses Selenium WebDriver with Java and TestNG for test execution and assertions. You can modify the code as per your specific requirements or add more test cases for different scenarios.