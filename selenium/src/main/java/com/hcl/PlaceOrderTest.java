import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @AfterMethod
    public void tearDown() {
        // Quit WebDriver
        driver.quit();
    }

    @Test
    public void testPlaceOrder() {
        // Click on Sign In link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set browser name and page name
        String browserName = "Customer Login";
        String pageName = "Customer Login";

        // Enter email and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on Sign In button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on Gear menu
        WebElement gearMenu = driver.findElement(By.className("gnav-link"));
        // Perform mouse hover using Actions class or JavaScriptExecutor

        // Click on Bags link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on Driven Backpack image
        WebElement drivenBackpackImg = driver.findElement(By.linkText("Driven Backpack"));
        drivenBackpackImg.click();

        // Click on Add to Cart button
        WebElement addToCartButton = driver.findElement(By.id("product_addtocart_form"));
        addToCartButton.submit();

        // Click on My Cart link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify Order Summary
        WebElement orderSummary = driver.findElement(By.xpath("//div[@class='cart-totals']"));
        Assert.assertTrue(orderSummary.getText().contains("Driven Backpack"));

        // Click on Proceed to Checkout button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[@class='button btn-checkout']"));
        proceedToCheckoutButton.click();

        // Click on New Address button
        WebElement newAddressButton = driver.findElement(By.id("checkout-step-billing"));
        newAddressButton.click();

        // Enter address details
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");

        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        // Select desired option from dropdown using Select class or JavaScriptExecutor

        WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
        postalCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on Ship Here button
        WebElement shipHereButton = driver.findElement(By.id("billing-shipping-buttons-container"));
        shipHereButton.click();

        // Select Fixed radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on Next button
        WebElement nextButton = driver.findElement(By.id("shipping-method-buttons-container"));
        nextButton.click();

        // Select My billing and shipping address are the same checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on Place Order button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // Verify Thank you message
        WebElement thankYouMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        Assert.assertEquals(thankYouMessage.getText(), "Thank you for your purchase!");

        // Click on Change button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // Click on Signout link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }
}
