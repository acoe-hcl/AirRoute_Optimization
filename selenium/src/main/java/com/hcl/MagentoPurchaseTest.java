import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class MagentoPurchaseTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Test Data
    private String baseURL = "https://magento2-demo.magebit.com/";
    private String validEmail = "testuser@example.com"; // please use a real/test Magento login
    private String validPassword = "TestUser123";
    private String street = "123 Main St";
    private String city = "San Jose";
    private String state = "California"; // Or the dropdown value as per the site
    private String zip = "95113";
    private String phone = "1234567890";

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testSuccessfulPurchaseFlow() {
        // Step 1: Open Magento website
        driver.get(baseURL);

        // Step 2: Click on "Sign In"
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In"))).click();

        // Step 3: Verify page and browser title
        wait.until(ExpectedConditions.titleContains("Customer Login"));
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Customer Login"), "Page title does not match.");

        // Step 4: Enter valid email and password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(validEmail);
        driver.findElement(By.id("pass")).sendKeys(validPassword);

        // Step 5: Click "Sign In"
        driver.findElement(By.id("send2")).click();

        // Step 6: Hover over "Gear" > Click "Bags"
        WebElement gearMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Gear']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bags"))).click();

        // Step 7: Click on "Driven Backpack" product image
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Driven Backpack"))).click();

        // Step 8: Click "Add to Cart"
        wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button"))).click();

        // Step 9: Click on "My Cart"
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".showcart"))).click();

        // Step 10: Verify "Order Summary" includes "Driven Backpack"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".minicart-items")));
        String cartText = driver.findElement(By.cssSelector(".minicart-items")).getText();
        Assert.assertTrue(cartText.contains("Driven Backpack"), "Order Summary does not contain 'Driven Backpack'.");

        // Step 11: Click "Proceed to Checkout"
        wait.until(ExpectedConditions.elementToBeClickable(By.id("top-cart-btn-checkout"))).click();

        // Step 12: Click "New Address" (if applicable)
        // Only click if button is present:
        try {
            WebElement newAddressBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='New Address']")));
            newAddressBtn.click();
        } catch (TimeoutException e) {
            // Address form is already shown or no button present
        }

        // Step 13: Fill in address (wait for form)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("street[0]"))).sendKeys(street);
        driver.findElement(By.name("city")).sendKeys(city);
        Select stateDropdown = new Select(driver.findElement(By.name("region_id")));
        stateDropdown.selectByVisibleText(state);
        driver.findElement(By.name("postcode")).sendKeys(zip);
        driver.findElement(By.name("telephone")).sendKeys(phone);

        // Step 14: Click "Ship Here"
        driver.findElement(By.xpath("//button/span[contains(text(),'Ship Here')]")).click();

        // Step 15: Wait shipping methods to load > Select "Fixed" radio
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ko_unique_1")));
        driver.findElement(By.cssSelector("input[type='radio'][value*='flatrate_flatrate']")).click();

        // Step 16: Click "Next"
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.continue"))).click();

        // Step 17: Wait for Payment page > select "My billing and shipping address are the same"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("billing-address-same-as-shipping"))).click();

        // Step 18: Click "Place Order"
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.checkout"))).click();

        // Step 19: Verify confirmation message
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".checkout-success")));
        Assert.assertTrue(confirmation.getText().contains("Thank you for your purchase!"),
                "Confirmation message not displayed or incorrect!");

        // Step 20: Click "Change" button (Assuming it's to edit order/address. If not present, skip)
        try {
            WebElement changeBtn = confirmation.findElement(By.xpath(".//button/span[contains(text(),'Change')]"));
            changeBtn.click();
        } catch (NoSuchElementException e) {
            // Not crucial for positive test
        }

        // Step 21: Sign out
        WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".customer-welcome")));
        accountDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out"))).click();
        wait.until(ExpectedConditions.titleContains("Home Page")); // verify sign-out succeeded
    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
