import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PlaceOrderTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set the path of the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        
        // Initialize ChromeDriver instance
        driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();
    }
    
    @Test
    public void placeOrderTest() {
        // Given User navigates to "https://magento.softwaretestingboard.com/"
        driver.get("https://magento.softwaretestingboard.com/");
        
        // When I click on the "Sign In" link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        
        // Then I should set the browser name as "Customer Login"
        String expectedBrowserName = "Customer Login";
        String actualBrowserName = driver.getTitle();
        Assert.assertEquals(actualBrowserName, expectedBrowserName);
        
        // And I should set the page name as "Customer Login"
        String expectedPageName = "Customer Login";
        String actualPageName = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(actualPageName, expectedPageName);
        
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
        WebElement gearMenu = driver.findElement(By.className("gear"));
        // Implement mouse hover action
        
        // And I click on the "Bags" link
        WebElement bagsLink = driver.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // When I click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.xpath("//img[@alt='Driven Backpack']"));
        drivenBackpackImage.click();
        
        // When I click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();
        
        // And I click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.linkText("My Cart"));
        myCartLink.click();
        
        // And I verify that the "Order Summary" is having "Driven Backpack" product
        WebElement orderSummary = driver.findElement(By.id("checkout-review-table"));
        String expectedProduct = "Driven Backpack";
        Assert.assertTrue(orderSummary.getText().contains(expectedProduct));
        
        // And I click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.id("onepage-guest-register-button"));
        proceedToCheckoutButton.click();
        
        // When I click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("billing:street1"));
        newAddressButton.sendKeys("4 South Street");
        
        // And I enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("billing:city"));
        cityField.sendKeys("Texas");
        
        // And I select "Texas" from the "State/Province" dropdown
        WebElement stateDropDown = driver.findElement(By.id("billing:region_id"));
        // Select "Texas" from the dropdown
        
        // And I enter "77567" in the "Zip/Postal Code" field
        WebElement postalCodeField = driver.findElement(By.id("billing:postcode"));
        postalCodeField.sendKeys("77567");
        
        // And I enter "3456788765" in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("billing:telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        // And I click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.id("billing:use_for_shipping_yes"));
        shipHereButton.click();
        
        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("p_method_checkmo"));
        fixedRadioButton.click();
        
        // And I click on the "Next" button
        WebElement nextButton = driver.findElement(By.xpath("//button[@title='Next']"));
        nextButton.click();
        
        // And I select the "My billing and shipping address are the same" checkbox
        WebElement sameBillingShippingCheckbox = driver.findElement(By.id("billing:use_for_shipping_yes"));
        sameBillingShippingCheckbox.click();
        
        // And I click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderButton.click();
        
        // And I verify the message "Thank you for your purchase!"
        WebElement successMessage = driver.findElement(By.className("success-msg"));
        String expectedMessage = "Thank you for your purchase!";
        Assert.assertEquals(successMessage.getText(), expectedMessage);
        
        // When I click on the "Change" button
        WebElement changeButton = driver.findElement(By.id("review-continue"));
        changeButton.click();
        
        // And I click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }

    @AfterMethod
    public void teardown() {
        // Close the browser window
        driver.quit();
    }
}
