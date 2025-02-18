import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class MagentoTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path of chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
        // Maximize browser window
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

    @Test
    public void testMagentoWebsite() {
        // Open the Magento website
        driver.get("https://www.example.com");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Verify that the browser name and page name is set as "Customer Login"
        String actualBrowserName = driver.getTitle();
        String actualPageName = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(actualBrowserName, "Customer Login");
        Assert.assertEquals(actualPageName, "Customer Login");

        // Enter the email address and password in the designated fields
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        emailField.sendKeys("testermail@gmail.com");
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.className("gear-menu"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).build().perform();
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the image of the "Driven Backpack" product
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.className("order-summary"));
        String orderSummaryText = orderSummary.getText();
        Assert.assertTrue(orderSummaryText.contains("Driven Backpack"));

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.cssSelector(".checkout-methods-items .primary"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing-address-select"));
        newAddressButton.click();
        
        // Enter address details
        WebElement streetAddressField = driver.findElement(By.id("street_1"));
        WebElement cityField = driver.findElement(By.id("city"));
        WebElement stateField = driver.findElement(By.id("region_id"));
        WebElement zipCodeField = driver.findElement(By.id("zip"));
        WebElement phoneNumberField = driver.findElement(By.id("telephone"));
        streetAddressField.sendKeys("123 Test Street");
        cityField.sendKeys("Test City");
        stateField.sendKeys("Test State");
        zipCodeField.sendKeys("12345");
        phoneNumberField.sendKeys("1234567890");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.xpath("//div[@id='shipping-new-address-form']//button"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']//button"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector("#payment-buttons-container .checkout-agreements"));
        placeOrderButton.click();

        // Verify that a confirmation message saying "Thank you for your purchase!" is displayed
        WebElement confirmationMessage = driver.findElement(By.cssSelector(".checkout-success .subtitle"));
        String actualMessage = confirmationMessage.getText();
        Assert.assertEquals(actualMessage, "Thank you for your purchase!");

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//a[@data-action='change-address']"));
        changeButton.click();

        // Click on the "Sign Out" link
        WebElement signOutLink = driver.findElement(By.linkText("Sign out"));
        signOutLink.click();
    }
}
