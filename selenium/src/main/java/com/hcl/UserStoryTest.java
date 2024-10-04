import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UserStoryTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        // Disable Chrome browser notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        // Launch Chrome browser
        driver = new ChromeDriver(options);
        // Implicitly wait for elements to load
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void placeOrderTest() {
        // Navigate to Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Verify that the browser name and page name are set as "Customer Login"
        String browserName = driver.getTitle();
        Assert.assertEquals(browserName, "Customer Login");

        // Enter email address and password
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.cssSelector(".gear-menu"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();
        WebElement bagsLink = driver.findElement(By.xpath("//a[contains(text(),'Bags')]"));
        bagsLink.click();

        // Click on the image of the "Driven Backpack"
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.xpath("//a[contains(text(),'My Cart')]"));
        myCartLink.click();

        // Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.xpath("//h2[contains(text(),'Order Summary')]"));
        Assert.assertTrue(orderSummary.getText().contains("Driven Backpack"));

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//span[contains(text(),'Proceed to Checkout')]"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing-address-select"));
        newAddressButton.click();

        // Enter address details
        WebElement streetAddressField = driver.findElement(By.id("billing:street1"));
        streetAddressField.sendKeys("123 Test Street");
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Test City");
        WebElement stateField = driver.findElement(By.id("billing:region_id"));
        stateField.sendKeys("Test State");
        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("12345");
        WebElement phoneField = driver.findElement(By.id("billing:telephone"));
        phoneField.sendKeys("1234567890");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-form-submit"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.cssSelector(".button[title='Next']"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing-address-same-as-shipping-checkbox"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector(".button[title='Place Order']"));
        placeOrderButton.click();

        // Verify the confirmation message
        WebElement confirmationMessage = driver.findElement(By.xpath("//h1[contains(text(),'Thank you for your purchase!')]"));
        Assert.assertEquals(confirmationMessage.getText(), "Thank you for your purchase!");

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.cssSelector(".button[title='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
