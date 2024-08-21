As a code assistant named Guru, I can help you generate the Test Automation Script for the given scenario using Selenium with Java and TestNG. Here's the code:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class MagentoOrderTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set the path of chromedriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

    @Test
    public void placeOrderTest() {
        // Navigate to the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        // Click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        // Set the browser name as "Customer Login"
        driver.manage().deleteCookieNamed("CUSTOMER_BROWSER_NAME");
        driver.manage().addCookie(new Cookie("CUSTOMER_BROWSER_NAME", "Customer Login"));

        // Set the page name as "Customer Login"
        driver.manage().deleteCookieNamed("CUSTOMER_PAGE_NAME");
        driver.manage().addCookie(new Cookie("CUSTOMER_PAGE_NAME", "Customer Login"));

        // Enter email in the "Email" field
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testermail@gmail.com");

        // Enter password in the "Password" field
        WebElement passwordField = driver.findElement(By.id("pass"));
        passwordField.sendKeys("Tester@123");

        // Click on the "Sign In" button
        WebElement signInButton = driver.findElement(By.id("send2"));
        signInButton.click();

        // Mouse hover on the "Gear" menu
        WebElement gearMenu = driver.findElement(By.className("gear-image"));
        Actions action = new Actions(driver);
        action.moveToElement(gearMenu).perform();

        // Click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();

        // Click on the "Driven Backpack" image
        WebElement drivenBackpack = driver.findElement(By.id("product-collection-image-9"));
        drivenBackpack.click();

        // Click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();

        // Click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.className("skip-cart"));
        myCartLink.click();

        // Verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.linkText("Driven Backpack"));
        String productName = orderSummary.getText();
        Assert.assertEquals(productName, "Driven Backpack");

        // Click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//button[text()='Proceed to Checkout']"));
        proceedToCheckoutButton.click();

        // Click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("address-id-billing-new"));
        newAddressButton.click();

        // Enter street in the "Street" field
        WebElement streetField = driver.findElement(By.id("billing:street1"));
        streetField.sendKeys("4 South Street");

        // Enter city in the "City" field
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");

        // Select state from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("billing:region_id"));
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText("Texas");

        // Enter zip code in the "Zip/Postal Code" field
        WebElement zipCodeField = driver.findElement(By.id("billing:postcode"));
        zipCodeField.sendKeys("77567");

        // Enter phone number in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");

        // Click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing-buttons-container"));
        shipHereButton.click();

        // Select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.xpath("//input[@value='fixed']"));
        fixedRadioButton.click();

        // Click on the "Next" button
        WebElement nextButton = driver.findElement(By.id("billing-next-button"));
        nextButton.click();

        // Select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameAddressCheckbox.click();

        // Click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[text()='Place Order']"));
        placeOrderButton.click();

        // Verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.xpath("//h1[text()='Thank you for your purchase!']"));
        Assert.assertTrue(successMessage.isDisplayed());

        // Click on the "Change" button
        WebElement changeButton = driver.findElement(By.xpath("//button[text()='Change']"));
        changeButton.click();

        // Click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }
}
```

Note: Make sure to replace "path/to/chromedriver" with the actual path of the chromedriver executable on your system.

This code contains the logic to perform various actions mentioned in the scenario and also includes assertions to validate the expected results. You can run this test script using a TestNG test runner to execute the test and generate the desired test combinations and assertions.