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
    public void setUp() {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

    @Test(priority = 1)
    public void testNavigateToMagentoWebsite() {
        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");
        // Verify if the page title is correct
        Assert.assertEquals(driver.getTitle(), "Home page");
    }

    @Test(priority = 2)
    public void testSignIn() {
        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();
        // Verify if the browser name and page name are set correctly
        Assert.assertEquals(driver.findElement(By.className("page-title")).getText(), "Customer Login");
    }

    @Test(priority = 3)
    public void testEnterCredentials() {
        // Enter email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");
        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();
    }

    @Test(priority = 4)
    public void testClickMenuAndBagsLink() {
        // Hover over the "Gear" menu
        WebElement gearMenu = driver.findElement(By.xpath("//nav[@id='nav']//span[text()='Gear']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();
        // Click on the "Bags" link
        driver.findElement(By.linkText("Bags")).click();
    }

    @Test(priority = 5)
    public void testClickProductAndAddToCart() {
        // Click on the image of the "Driven Backpack"
        driver.findElement(By.cssSelector("a[title='Driven Backpack']")).click();
        // Click on the "Add to Cart" button
        driver.findElement(By.id("product-addtocart-button")).click();
    }

    @Test(priority = 6)
    public void testClickMyCart() {
        // Click on the "My Cart" link
        driver.findElement(By.linkText("My Cart")).click();
        // Verify if the "Order Summary" includes the "Driven Backpack" product
        Assert.assertTrue(driver.findElement(By.linkText("Driven Backpack")).isDisplayed());
    }

    @Test(priority = 7)
    public void testProceedToCheckout() {
        // Click on the "Proceed to Checkout" button
        driver.findElement(By.id("top-cart-btn-checkout")).click();
    }

    @Test(priority = 8)
    public void testEnterAddressInformation() {
        // Click on the "New Address" button
        driver.findElement(By.id("billing:street1")).sendKeys("Street Address");
        driver.findElement(By.id("billing:city")).sendKeys("City");
        driver.findElement(By.id("billing:region")).sendKeys("State/Province");
        driver.findElement(By.id("billing:postcode")).sendKeys("Zip/Postal Code");
        driver.findElement(By.id("billing:telephone")).sendKeys("Phone Number");
        // Click on the "Ship Here" button
        driver.findElement(By.id("address-choice-shipto")).click();
    }

    @Test(priority = 9)
    public void testSelectShippingOption() {
        // Select the "Fixed" radio button
        driver.findElement(By.id("shipping:fixed_flat")).click();
        // Click on the "Next" button
        driver.findElement(By.id("shipping-method-buttons-container")).click();
    }

    @Test(priority = 10)
    public void testSelectBillingAddress() {
        // Select the "My billing and shipping address are the same" checkbox
        driver.findElement(By.id("billing:use_for_shipping_yes")).click();
        // Click on the "Place Order" button
        driver.findElement(By.id("payment-buttons-container")).click();
    }

    @Test(priority = 11)
    public void testVerifyConfirmationMessage() {
        // Verify if the confirmation message is displayed
        WebElement confirmationMessage = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals(confirmationMessage.getText(), "Thank you for your purchase!");
    }

    @Test(priority = 12)
    public void testSignOut() {
        // Click on the "Change" button
        driver.findElement(By.cssSelector(".right > button")).click();
        // Click on the "Signout" link
        driver.findElement(By.linkText("Sign out")).click();
    }
}
