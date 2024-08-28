As a professional code assistant named Guru, I can help you generate a Test Automation Script for the given feature file content using Selenium Java with TestNG. Here's the generated code:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MagentoOrderTest {
    private WebDriver driver;
    
    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "path/to/chrome/driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test(priority = 1)
    public void loginAndPlaceOrderTest() {
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
        // And I click on the "Bags" link
        WebElement gearMenu = driver.findElement(By.cssSelector(".nav-6"));
        WebElement bagsLink = gearMenu.findElement(By.linkText("Bags"));
        bagsLink.click();
        
        // When I click on the "Driven Backpack" image
        WebElement drivenBackpackImage = driver.findElement(By.cssSelector(".product-image-photo[alt='Driven Backpack']"));
        drivenBackpackImage.click();
        
        // When I click on the "Add to Cart" button
        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        addToCartButton.click();
        
        // And I click on the "My Cart" link
        WebElement myCartLink = driver.findElement(By.id("minicart-content-wrapper"));
        myCartLink.click();
        
        // And I verify that the "Order Summary" is having "Driven Backpack" product
        String expectedProduct = "Driven Backpack";
        WebElement orderSummaryElement = driver.findElement(By.cssSelector(".table-totals > tbody > tr > td"));
        String actualProduct = orderSummaryElement.getText();
        Assert.assertTrue(actualProduct.contains(expectedProduct));
        
        // And I click on the "Proceed to Checkout" button
        WebElement proceedToCheckoutButton = driver.findElement(By.cssSelector(".action.primary.checkout"));
        proceedToCheckoutButton.click();
        
        // When I click on the "New Address" button
        WebElement newAddressButton = driver.findElement(By.id("shipping-new-address-form-popup"));
        newAddressButton.click();
        
        // And I enter "4 South Street" in the "Street" field
        WebElement streetField = driver.findElement(By.id("shipping:street1"));
        streetField.sendKeys("4 South Street");
        
        // And I enter "Texas" in the "City" field
        WebElement cityField = driver.findElement(By.id("shipping:city"));
        cityField.sendKeys("Texas");
        
        // And I select "Texas" from the "State/Province" dropdown
        WebElement stateDropdown = driver.findElement(By.id("shipping:region_id"));
        WebElement texasOption = stateDropdown.findElement(By.xpath("//option[text()='Texas']"));
        texasOption.click();
        
        // And I enter "77567" in the "Zip/Postal Code" field
        WebElement postalCodeField = driver.findElement(By.id("shipping:postcode"));
        postalCodeField.sendKeys("77567");
        
        // And I enter "3456788765" in the "Phone Number" field
        WebElement phoneNumberField = driver.findElement(By.id("shipping:telephone"));
        phoneNumberField.sendKeys("3456788765");
        
        // And I click on the "Ship Here" button
        WebElement shipHereButton = driver.findElement(By.cssSelector(".action.action-primary"));
        shipHereButton.click();
        
        // And I select the "Fixed" radio button
        WebElement fixedRadioButton = driver.findElement(By.id("s_method_flatrate_flatrate"));
        fixedRadioButton.click();
        
        // And I click on the "Next" button
        WebElement nextButton = driver.findElement(By.cssSelector(".button.action.next.primary"));
        nextButton.click();
        
        // And I select the "My billing and shipping address are the same" checkbox
        WebElement sameAddressCheckbox = driver.findElement(By.cssSelector(".checkbox[for='billing:same_as_shipping']"));
        sameAddressCheckbox.click();
        
        // And I click on the "Place Order" button
        WebElement placeOrderButton = driver.findElement(By.cssSelector(".action.checkout.primary"));
        placeOrderButton.click();
        
        // And I verify the message "Thank you for your purchase!"
        String expectedMessage = "Thank you for your purchase!";
        WebElement successMessageElement = driver.findElement(By.cssSelector(".message.success"));
        String actualMessage = successMessageElement.getText();
        Assert.assertEquals(actualMessage, expectedMessage);
        
        // When I click on the "Change" button
        WebElement changeButton = driver.findElement(By.cssSelector(".tocart.change-mode > span"));
        changeButton.click();
        
        // And I click on the "Signout" link
        WebElement signoutLink = driver.findElement(By.linkText("Signout"));
        signoutLink.click();
    }
    
    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
```

This code provides the TestNG annotations for setup, test execution, and teardown. It navigates to the specified URL, performs the actions mentioned in the feature file, and includes assertions to verify expected results.