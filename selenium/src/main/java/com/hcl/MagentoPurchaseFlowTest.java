import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class MagentoPurchaseFlowTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Initialize the Chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSuccessfulPurchaseFlow() {
        // Open the Magento website
        driver.get("https://magento.com/");
        Assert.assertTrue(driver.getTitle().contains("Magento"), "Magento website did not open.");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Verify browser name and page name is "Customer Login"
        WebElement pageTitle = driver.findElement(By.xpath("//h1[contains(text(),'Customer Login')]"));
        Assert.assertTrue(pageTitle.isDisplayed(), "Customer Login page is not displayed.");

        // Enter valid email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        emailField.sendKeys("validuser@example.com");
        passwordField.sendKeys("ValidPassword123");

        // Click on "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Hover over "Gear" menu
        Actions actions = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.xpath("//span[text()='Gear']"));
        actions.moveToElement(gearMenu).perform();

        // Click on "Bags" link
        WebElement bagsLink = driver.findElement(By.xpath("//span[text()='Bags']"));
        bagsLink.click();

        // Click on "Driven Backpack" product image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on "Add to Cart" button
        WebElement addToCartBtn = driver.findElement(By.id("product-addtocart-button"));
        addToCartBtn.click();

        // Click on "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[contains(@class,'showcart')]"));
        myCartLink.click();

        // Verify "Order Summary" includes "Driven Backpack"
        WebElement orderSummaryProduct = driver.findElement(By.xpath("//strong[@class='product-item-name']/a[text()='Driven Backpack']"));
        Assert.assertTrue(orderSummaryProduct.isDisplayed(), "Driven Backpack not found in the Order Summary.");

        // Click on "Proceed to Checkout" button
        WebElement checkoutBtn = driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout']"));
        checkoutBtn.click();

        // Click on "New Address" button
        WebElement newAddressBtn = driver.findElement(By.xpath("//button/span[text()='New Address']/.."));
        newAddressBtn.click();

        // Enter address details
        driver.findElement(By.name("street[0]")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Los Angeles");
        driver.findElement(By.name("region_id")).sendKeys("California");
        driver.findElement(By.name("postcode")).sendKeys("90001");
        driver.findElement(By.name("telephone")).sendKeys("5551234567");

        // Click on "Ship Here" button
        WebElement shipHereBtn = driver.findElement(By.xpath("//button/span[text()='Ship Here']/.."));
        shipHereBtn.click();

        // Select "Fixed" radio button for shipping method
        WebElement fixedRadioBtn = driver.findElement(By.xpath("//input[@type='radio' and @value='flatrate_flatrate']"));
        fixedRadioBtn.click();

        // Click on "Next" button
        WebElement nextBtn = driver.findElement(By.xpath("//button/span[text()='Next']/.."));
        nextBtn.click();

        // Select "My billing and shipping address are the same" checkbox
        WebElement addressSameCheckbox = driver.findElement(By.name("billing-address-same-as-shipping"));
        if (!addressSameCheckbox.isSelected()) {
            addressSameCheckbox.click();
        }

        // Click on "Place Order" button
        WebElement placeOrderBtn = driver.findElement(By.xpath("//button/span[text()='Place Order']/.."));
        placeOrderBtn.click();

        // Verify confirmation message "Thank you for your purchase!" is displayed
        WebElement confirmationMsg = driver.findElement(By.xpath("//*[contains(text(),'Thank you for your purchase!')]"));
        Assert.assertTrue(confirmationMsg.isDisplayed(), "Purchase confirmation message is not displayed.");

        // Click on "Change" button
        WebElement changeBtn = driver.findElement(By.xpath("//button/span[text()='Change']/.."));
        changeBtn.click();

        // Click on "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Sign Out"));
        signoutLink.click();

        // Verify user is signed out (e.g., "Sign In" link displayed again)
        Assert.assertTrue(driver.findElement(By.linkText("Sign In")).isDisplayed(), "User did not sign out successfully.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
