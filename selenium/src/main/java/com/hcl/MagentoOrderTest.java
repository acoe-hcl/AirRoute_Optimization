import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up browser and navigate to Magento website
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @Test(priority = 0)
    public void testLogin() {
        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name and page name
        String browserName = "Customer Login";
        String pageName = "Customer Login";
        driver.executeScript("window.localStorage.setItem('browserName', '" + browserName + "')");
        driver.executeScript("window.localStorage.setItem('pageName', '" + pageName + "')");

        // Enter email and password in the fields
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();
    }

    @Test(priority = 1)
    public void testPlaceOrder() {
        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.cssSelector(".nav-2>a"));
        Actions actions = new Actions(driver);
        actions.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.cssSelector(".product-image-photo"));
        drivenBackpackImage.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.cssSelector(".minicart-wrapper .showcart"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.cssSelector(".cart-table tbody"));
        String orderSummaryText = orderSummary.getText();
        Assert.assertTrue(orderSummaryText.contains("Driven Backpack"));

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing:street1"));
        newAddressButton.sendKeys("4 South Street");

        // Enter address details
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
	Select stateSelect = new Select(stateDropdown);
	stateSelect.selectByVisibleText("Texas");

        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("77567");

        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.cssSelector(".radio:nth-child(1) input"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("billing-buttons-container"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector(".review-actions .button"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement confirmationMessage = driver.findElement(By.cssSelector(".success-msg span"));
        String message = confirmationMessage.getText();
        Assert.assertEquals(message, "Thank you for your purchase!");

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.cssSelector(".col-2 button"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
