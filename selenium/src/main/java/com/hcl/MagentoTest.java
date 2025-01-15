import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MagentoTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path of the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        // Launch Chrome browser
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testMagentoWebsite() {
        // Open the Magento website
        driver.get("https://example.com");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Verify that the browser name and page name is set as "Customer Login"
        String expectedBrowserName = "Customer Login";
        String actualBrowserName = driver.getTitle();
        Assert.assertEquals(actualBrowserName, expectedBrowserName);

        // Enter the email address and password in the designated fields
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("signin-button"));
        signInButton.click();

        // Hover over the "Gear" menu and click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.id("gear-menu"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the image of the "Driven Backpack" product
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // Verify that the "Order Summary" includes the "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.id("order-summary"));
        Assert.assertTrue(orderSummary.getText().contains("Driven Backpack"));

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("proceed-to-checkout-button"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("new-address-button"));
        newAddressButton.click();

        // Enter the shipping address details
        WebElement streetAddressField = driver.findElement(By.id("street-address"));
        streetAddressField.sendKeys("123 Main Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("New York");

        WebElement stateField = driver.findElement(By.id("state"));
        stateField.sendKeys("New York");

        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        postalCodeField.sendKeys("10001");

        WebElement phoneNumberField = driver.findElement(By.id("phone-number"));
        phoneNumberField.sendKeys("1234567890");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("ship-here-button"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("fixed-radio-button"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("next-button"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement billingAddressCheckbox = driver.findElement(By.id("billing-address-checkbox"));
        billingAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.id("place-order-button"));
        placeOrderButton.click();

        // Verify that a confirmation message saying "Thank you for your purchase!" is displayed
        WebElement confirmationMessage = driver.findElement(By.id("confirmation-message"));
        String expectedConfirmationMessage = "Thank you for your purchase!";
        Assert.assertEquals(confirmationMessage.getText(), expectedConfirmationMessage);

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.id("change-button"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signOutLink = driver.findElement(By.linkText("Signout"));
        signOutLink.click();
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser window
        driver.quit();
    }
}
