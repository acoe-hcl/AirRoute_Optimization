import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setup() {
        // Set up Chrome driver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void placeOrderTest() {
        driver.get("https://magento.softwaretestingboard.com/");

        // Sign in
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.name("login[username]")).sendKeys("testermail@gmail.com");
        driver.findElement(By.name("login[password]")).sendKeys("Tester@123");
        driver.findElement(By.id("send2")).click();
        
        // Navigate to product and add to cart
        WebElement gearMenu = driver.findElement(By.xpath("//span[@class='name'][contains(text(),'Gear')]"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();
        driver.findElement(By.linkText("Bags")).click();
        driver.findElement(By.linkText("Driven Backpack")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        driver.findElement(By.linkText("My Cart")).click();
        
        // Verify product in order summary
        WebElement orderSummary = driver.findElement(By.id("shopping-cart-table"));
        Assert.assertTrue(orderSummary.getText().contains("Driven Backpack"), "Order summary does not contain Driven Backpack");
        
        // Proceed to checkout
        driver.findElement(By.id("onepage-checkout")).click();
        
        // Fill shipping address
        driver.findElement(By.cssSelector("#billing-buttons-container button[onclick*='shipping']")).click();
        driver.findElement(By.id("billing:street1")).sendKeys("4 South Street");
        driver.findElement(By.id("billing:city")).sendKeys("Texas");
        driver.findElement(By.id("billing:region_id")).sendKeys("Texas");
        driver.findElement(By.id("billing:postcode")).sendKeys("77567");
        driver.findElement(By.id("billing:telephone")).sendKeys("3456788765");
        driver.findElement(By.cssSelector("#billing-buttons-container button[onclick*='shipping']")).click();
        
        // Select shipping method
        driver.findElement(By.id("s_method_flatrate_flatrate")).click();
        driver.findElement(By.cssSelector("#shipping-buttons-container button[onclick*='payment']")).click();
        
        // Select billing and shipping address
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        driver.findElement(By.cssSelector("#payment-buttons-container button[onclick*='submit(']")).click();
        
        // Verify purchase message
        WebElement purchaseMessage = driver.findElement(By.xpath("//*[contains(text(),'Thank you for your purchase!')]"));
        Assert.assertTrue(purchaseMessage.isDisplayed(), "Purchase message is not displayed");
        
        // Sign out
        driver.findElement(By.linkText("Change")).click();
        driver.findElement(By.linkText("Signout")).click();
    }
    
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
