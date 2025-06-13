import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SuccessfulPurchaseFlowTest {
    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://magento2-demo.magebit.com"; // Example URL; update as needed

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver"); // or chromedriver.exe
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testSuccessfulPurchaseFlow() {
        // STEP 1: Open the Magento website.
        driver.get(baseUrl);

        // STEP 2: Click on the "Sign In" link.
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In"))).click();
        
        // STEP 3: Verify browser title and page.
        String expectedTitle = "Customer Login";
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Browser title should contain 'Customer Login'.");

        // STEP 4: Enter email and password.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
                .sendKeys("testuser@example.com"); // Replace with valid email
        driver.findElement(By.id("pass")).sendKeys("TestPassword123!"); // Replace with valid password

        // STEP 5: Click "Sign In"
        driver.findElement(By.id("send2")).click();

        // STEP 6: Hover over "Gear" menu -> Click "Bags"
        By gearMenuLocator = By.xpath("//span[text()='Gear']");
        By bagsLinkLocator = By.xpath("//span[text()='Bags']");
        Actions actions = new Actions(driver);
        WebElement gearMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(gearMenuLocator));
        actions.moveToElement(gearMenu).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(bagsLinkLocator)).click();

        // STEP 7: Click on "Driven Backpack" image
        By backpackImage = By.xpath("//img[@alt='Driven Backpack']"); // Update locator as needed
        wait.until(ExpectedConditions.elementToBeClickable(backpackImage)).click();

        // STEP 8: Click "Add to Cart"
        By addToCartBtn = By.id("product-addtocart-button");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();

        // STEP 9: Click "My Cart" link
        By myCartLink = By.xpath("//a[@class='action showcart']");
        wait.until(ExpectedConditions.elementToBeClickable(myCartLink)).click();

        // STEP 10: Verify Order Summary includes "Driven Backpack"
        By orderSummaryProdName = By.xpath("//strong[contains(@class, 'product-item-name') and text()='Driven Backpack']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSummaryProdName));
        Assert.assertTrue(driver.findElement(orderSummaryProdName).isDisplayed(),
                "Order Summary should list 'Driven Backpack'.");

        // STEP 11: Click "Proceed to Checkout"
        By proceedToCheckoutBtn = By.xpath("//button[@id='top-cart-btn-checkout']");
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();

        // STEP 12: Click "New Address" button if necessary
        try {
            By newAddressBtn = By.xpath("//button[@data-bind='click: showAddressForm']");
            wait.until(ExpectedConditions.elementToBeClickable(newAddressBtn)).click();
        } catch (TimeoutException ex) {
            // New Address button not present; possibly the user must always enter address in demo
        }

        // STEP 13: Enter Address Details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("street[0]"))).sendKeys("123 Test Lane");
        driver.findElement(By.name("city")).sendKeys("Testville");
        // Select State/Province
        Select stateDropdown = new Select(driver.findElement(By.name("region_id")));
        stateDropdown.selectByVisibleText("California"); // Change as needed
        driver.findElement(By.name("postcode")).sendKeys("90001");
        driver.findElement(By.name("telephone")).sendKeys("555-123-4567");

        // STEP 14: Click "Ship Here"
        By shipHereBtn = By.xpath("//button[@data-action='customer-address-save']");
        wait.until(ExpectedConditions.elementToBeClickable(shipHereBtn)).click();

        // STEP 15: Select "Fixed" shipping method
        By fixedShippingRadio = By.xpath("//input[@type='radio' and contains(@value, 'flatrate_flatrate')]");
        wait.until(ExpectedConditions.elementToBeClickable(fixedShippingRadio)).click();

        // STEP 16: Click "Next"
        By nextBtn = By.xpath("//button[@data-role='opc-continue']");
        wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();

        // STEP 17: Select "My billing and shipping address are the same"
        By billingSameCheckbox = By.xpath("//input[@name='billing-address-same-as-shipping']");
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(billingSameCheckbox));
        if (!checkbox.isSelected()) checkbox.click();

        // STEP 18: Click "Place Order"
        By placeOrderBtn = By.xpath("//button[@title='Place Order']");
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();

        // STEP 19: Verify confirmation message
        By confirmationMsg = By.xpath("//span[contains(text(),'Thank you for your purchase!')]");
        WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsg));
        Assert.assertTrue(confirm.isDisplayed(), "Confirmation message should be displayed.");

        // STEP 20: Click "Change" button (if present)
        try {
            By changeBtn = By.xpath("//button[contains(text(),'Change')]");
            wait.until(ExpectedConditions.elementToBeClickable(changeBtn)).click();
        } catch (TimeoutException ex) {
            // Not present on the confirmation page; skip
        }

        // STEP 21: Click "Signout" link
        By accountMenu = By.xpath("//span[@class='customer-name']");
        By signOutLink = By.xpath("//a[text()='Sign Out']");
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(accountMenu))).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signOutLink)).click();

        // Final Assertion: Ensure Sign In is available after sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign In")));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
