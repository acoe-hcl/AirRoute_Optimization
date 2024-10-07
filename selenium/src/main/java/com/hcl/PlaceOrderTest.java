import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set driver path for Chrome browser
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() {
        // Close the browser
        driver.quit();
    }

    @Test
    public void placeOrderTest() throws InterruptedException {
        // Given User navigates to "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");

        // When I click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Then I should set the browser name as "Customer Login"
        String expectedBrowserName = "Customer Login";
        String actualBrowserName = driver.getTitle();
        // Assertion logic for browser name
        assert actualBrowserName.equals(expectedBrowserName) : "Browser name is not as expected";

        // And I should set the page name as "Customer Login"
        String expectedPageName = "Customer Login";
        String actualPageName = driver.findElement(By.tagName("h1")).getText();
        // Assertion logic for page name
        assert actualPageName.equals(expectedPageName) : "Page name is not as expected";

        // When I enter "testermail@gmail.com" in the "Email" field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        // And I enter "Tester@123" in the "Password" field as secure text
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // And I click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // When I mouse hover on the "Gear" menu
        Actions action = new Actions(driver);
        WebElement gearMenu = driver.findElement(By.className("top-link-gear"));
        action.moveToElement(gearMenu).perform();

        // And I click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // When I click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//a[@title='Driven Backpack']"));
        drivenBackpackImage.click();

        // When I click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // And I click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();

        // And I verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.id("checkout-cart-wrapper"));
        String expectedProduct = "Driven Backpack";
        // Assertion logic for order summary
        assert orderSummary.getText().contains(expectedProduct) : "Order summary does not contain the expected product";

        // And I click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("onepage-guest-register-button"));
        proceedToCheckoutButton.click();

        // When I click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.cssSelector("button[title='New Address']"));
        newAddressButton.click();

        // And I enter "4 South Street" in the "Street" field
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");

        // And I enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        // And I select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        stateDropdown.sendKeys("Texas");

        // And I enter "77567" in the "Zip/Postal Code" field
        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("77567");

        // And I enter "3456788765" in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // And I click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();

        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();

        // And I click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();

        // And I select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // And I click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();

        // And I verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.className("success-msg"));
        String expectedMessage = "Thank you for your purchase!";
        // Assertion logic for success message
        assert successMessage.getText().equals(expectedMessage) : "Success message is not as expected";

        // When I click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[@title='Change']"));
        changeButton.click();

        // And I click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }
}
